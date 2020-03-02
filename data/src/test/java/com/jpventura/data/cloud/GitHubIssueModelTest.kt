package com.jpventura.data.cloud

import com.jpventura.data.ktx.asUser
import com.jpventura.domain.bean.*
import com.jpventura.domain.model.VersionControlModel
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class GitHubIssueModelTest {

    private val testScheduler = TestScheduler()

    private lateinit var model: VersionControlModel.IssueModel

    private val owner = owner {
        avatarUrl = "https://avatars2.githubusercontent.com/u/878437?v=4"
        id = 878437L
        key = "JetBrains"
        name = "JetBrains"
        reposUrl = "https://github.com/jetBrains/kotlin"
        userUrl = "https://github.com/jetBrains"
    }

    private val issue = issue {
        id = 578390638L
        body =
            "Otherwise, file mappings in the source map would be accumulated, resulting in consistent shifting of line numbers (as shown at `smap.kt` test)."
        createdAt = Date()
        number = 3180
        repositoryId = "JetBrains/kotlin"
        title = "JVM_IR: use fresh source map when generating lambda body for inline."
        url = "https://api.github.com/repos/jetbrains/kotlin/issues/3180"
        user = this@GitHubIssueModelTest.owner.asUser()
    }

    private val repository = repository {
        description = "The Kotlin Programming Language"
        forks = 3727
        fullName = "JetBrains/kotlin"
        id = 878437L
        language = "Kotlin"
        name = "kotlin"
        owner = this@GitHubIssueModelTest.owner
        stargazersCount = 30918
        user = user {
            avatarUrl = "https://avatars2.githubusercontent.com/u/1833474?v=4"
            id = 878437L
            key = "jetbrains"
            name = "JetBrains"
        }
    }

    @Before
    fun setup() {
        val interceptors = listOf(GitHubBasicInterceptor())
        model = GitHubIssueModel(client = GitHubClient.create(interceptors = interceptors))

        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.setIoSchedulerHandler { null }
        RxJavaPlugins.setComputationSchedulerHandler(null)
    }

    @Test
    fun `Should not support create issues`() {
        model.create(listOf(issue))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support create one issue`() {
        model.createOne(issue)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support destroy issues`() {
        model.destroy(listOf(compoundKey(issue)))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support all repository issues`() {
        model.destroyOne(compoundKey(issue))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support replace or create issues`() {
        model.createOrUpdate(listOf(issue))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support replace or create one issue`() {
        model.createOrUpdateOne(issue)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `should find issues`() {
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
        model.containsValue(issue)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()

        model.containsValues(listOf(issue))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support update one issue`() {
        model.updateOne(issue)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support update issues bulk`() {
        model.update(listOf(issue))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    private fun compoundKey(value: Issue) = "${value.parentKey}/${value.id}"

    companion object {
        private const val MOCK_PORT = 6166
        private const val MOCK_URL = "http://localhost:${MOCK_PORT}"
    }

}
