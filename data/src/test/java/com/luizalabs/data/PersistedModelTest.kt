package com.luizalabs.data

import com.luizalabs.core.domain.bean.Bean
import com.luizalabs.core.domain.model.PersistedModel
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

abstract class PersistedModelTest<K, V : Bean<K>>(
    val samples: Map<K, V>
) {

    abstract val model: PersistedModel<K, V>

    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
    }

    @Test
    fun `Should clear no elements`() {
        model.size()
            .firstOrError()
            .test()
            .assertValue { it == 0 }
            .dispose()
    }

    @Test
    fun `Should listen to updates indefinitely`() {
        val testObserver = TestObserver<List<V>>()

        val values = samples.values.toList()

        val events = listOf(
            emptyList(),
            values,
            values.subList(1, values.size)
        )

        model.find().subscribeOn(testScheduler).subscribe(testObserver)
        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)

        model.createOrUpdate(values)
        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)

        model.destroyOne(values.first().id)
        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)

        testObserver.assertValues(*events.toTypedArray())
            .assertNoErrors()
            .assertNotComplete()
            .dispose()

        tearDownModel()
    }

    @Test
    fun `Should create elements`() {
        model.create(samples.values)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(samples.keys.toList())
            .dispose()

        tearDownModel()
    }

    @Test
    fun `Should create one element`() {
        val value = samples.values.first()

        model.createOne(value)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { it == value.id }
            .dispose()

        tearDownModel()
    }

    @Test
    fun `Should destroy all elements`() {
        setupModel()

        model.destroy(samples.keys)
            .flatMapCompletable { model.containsKeys(samples.keys) }
            .test()
            .assertError(NoSuchElementException::class.java)
            .dispose()

        tearDownModel()
    }

    @Test
    fun `Should find no elements`() {
        model.clear()
            .flatMapObservable { model.find() }
            .firstOrError()
            .test()
            .assertValue { it.isEmpty() }
            .assertComplete()
            .assertNoErrors()
            .dispose()
    }

    @Test
    fun `Should find no keys`() {
        model.keys()
            .firstOrError()
            .test()
            .assertValue { it.isEmpty() }
            .assertComplete()
            .assertNoErrors()
            .dispose()
    }

    @Test
    fun `Should find keys`() {
        setupModel()

        model.keys()
            .firstOrError()
            .test()
            .assertValue(samples.keys.toList())
            .assertComplete()
            .assertNoErrors()
            .dispose()

        tearDownModel()
    }

    @Test
    fun `Should be empty`() {
        tearDownModel()
    }

    @After
    fun tearDown() {
        tearDownModel()
        RxJavaPlugins.reset()
    }

    private fun setupModel() {
        model.createOrUpdate(samples.values)
            .flatMapObservable { model.find() }
            .firstOrError()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(samples.values.toList())
            .dispose()
    }

    private fun tearDownModel() {
        model.clear()
            .flatMapObservable {
                model.size()
            }
            .firstOrError()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(0)
            .dispose()
    }

}
