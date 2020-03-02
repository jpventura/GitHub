package com.jpventura.data.cloud

import com.jpventura.data.ktx.asUser
import com.jpventura.domain.bean.*
import com.jpventura.domain.model.VersionControlModel
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before

import org.junit.Test
import java.util.*

class GitHubPullModelTest {

    private val testScheduler = TestScheduler()

    private lateinit var model: VersionControlModel.PullModel

    private val owner = owner {
        avatarUrl = "https://avatars2.githubusercontent.com/u/878437?v=4"
        id = 878437L
        key = "JetBrains"
        name = "JetBrains"
        reposUrl = "https://github.com/jetBrains/kotlin"
        userUrl = "https://github.com/jetBrains"
    }

    private val pull = pullRequest {
        body = "Fixes [KT-37024](https://youtrack.jetbrains.com/pull/KT-37024)."
        createdAt = Date()
        id = 386625732L
        repositoryId = "JetBrains/kotlin"
        number = 3184
        state = "open"
        title = "JVM IR: Mangle interface implementation methods in inline classes"
        user = this@GitHubPullModelTest.owner.asUser()
    }

    private val repository = repository {
        description = "The Kotlin Programming Language"
        forks = 3727
        fullName = "JetBrains/kotlin"
        id = 3432266L
        language = "Kotlin"
        name = "kotlin"
        owner = this@GitHubPullModelTest.owner
        stargazersCount = 30918
        user = user {
            id = 878437L
            avatarUrl = "https://avatars2.githubusercontent.com/u/1833474?v=4"
            key = "jetbrains"
            name = "JetBrains"
        }
    }

    @Before
    fun setup() {
        val interceptors = listOf(GitHubBasicInterceptor())
        model = GitHubPullModel(client = GitHubClient.create(interceptors = interceptors))
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.setIoSchedulerHandler { null }
        RxJavaPlugins.setComputationSchedulerHandler(null)
    }

    @Test
    fun `Should not support create pulls`() {
        model.create(listOf(pull))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support create one pull`() {
        model.createOne(pull)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support destroy pulls`() {
        model.destroy(listOf(compoundKey(pull)))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support all repository pulls`() {
        model.destroyOne(compoundKey(pull))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support replace or create pulls`() {
        model.createOrUpdate(listOf(pull))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support replace or create one pull`() {
        model.createOrUpdateOne(pull)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `should find pulls`() {
        model.find(repository)
            .firstOrError()
            .toObservable()
            .flatMapIterable { it }
            .count()
            .test()
            .assertNoErrors()
            .assertValue { it > 0 }
            .dispose()
    }

    @Test
    fun `should not support contains values`() {
        model.containsValue(pull)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()

        model.containsValues(listOf(pull))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support update one pull`() {
        model.updateOne(pull)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support update pulls bulk`() {
        model.update(listOf(pull))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    private fun compoundKey(value: Pull) = "${value.parentKey}/${value.id}"

    companion object {
        private const val MOCK_PORT = 6166
        private const val MOCK_URL = "http://localhost:${MOCK_PORT}"
    }

}
