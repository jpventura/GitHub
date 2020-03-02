package com.jpventura.domain.interactor

import com.jpventura.domain.UseCases
import com.jpventura.domain.UseCases.FindRepositories.Param
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.kodein.domainModules
import com.jpventura.domain.model.REPOSITORIES
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class FindTrendingRepositoriesTest : KodeinAware {

    override val kodein = Kodein.lazy {
        import(domainModules())
    }

    private val testScheduler: TestScheduler by instance()
    private val subject = PublishSubject.create<List<Repository>>()
    private val interactor: UseCases.FindTrendingRepositories by instance()

    @Test
    fun `should not find missing languages repositories`() {
        val testObservable = TestObserver<List<Repository>>()

        interactor.invoke(Param(language = MISSING_LANGUAGE)).subscribe(testObservable)
        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)

        testObservable
            .assertNotComplete()
            .assertNoErrors()
            .assertValue { it.isEmpty() }
            .dispose()
    }

    @Test
    fun `should find contained languages`() {
        val testObservable = TestObserver<List<Repository>>()

        interactor.invoke(Param(language = CONTAINED_LANGUAGE))
            .subscribe(testObservable)
        testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)

        testObservable
            .assertNoErrors()
            .assertValue {
                it == REPOSITORIES.values.toList()
            }
            .dispose()
    }

    companion object {
        const val MISSING_LANGUAGE = "Cobol"
        const val CONTAINED_LANGUAGE = "Kotlin"
    }

}
