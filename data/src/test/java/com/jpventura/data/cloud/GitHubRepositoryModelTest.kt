package com.jpventura.data.cloud

import com.jpventura.domain.bean.owner
import com.jpventura.domain.bean.repository
import com.jpventura.domain.bean.user
import com.jpventura.domain.model.VersionControlModel
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import org.junit.After
import org.junit.Before

import org.junit.Test

class GitHubRepositoryModelTest {

    private val testScheduler = TestScheduler()

    private lateinit var model: VersionControlModel.RepositoryModel

    private val owner = owner {
        avatarUrl = "https://avatars2.githubusercontent.com/u/878437?v=4"
        id = 878437L
        key = "JetBrains"
        name = "JetBrains"
        reposUrl = "https://github.com/jetBrains/kotlin"
        userUrl = "https://github.com/jetBrains"
    }

    private val repository = repository {
        description = "The Kotlin Programming Language"
        forks = 3727
        fullName = "JetBrains/kotlin"
        id = 3432266L
        language = "Kotlin"
        name = "kotlin"
        owner = this@GitHubRepositoryModelTest.owner
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
        val interceptors = listOf(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()

                val credentials = Credentials.basic(
                    username = "jpventura",
                    password = "e29d37a5bd868e9d98f8712636d43f113084a4df"
                )
                return chain.proceed(request
                    .newBuilder()
                    .header(name ="Authorization", value = credentials)
                    .method(request.method, request.body)
                    .build())
            }
        })

        model = GitHubRepositoryModel(client = GitHubClient.create(interceptors = interceptors))

        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.setIoSchedulerHandler { null }
        RxJavaPlugins.setComputationSchedulerHandler(null)
    }

    @Test
    fun `Should not support create repositories`() {
        model.create(listOf(repository))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support create one repository`() {
        model.createOne(repository)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support destroy repositories`() {
        model.destroy(listOf(repository.key))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support destroy one repository`() {
        model.destroyOne(repository.key)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support replace or create repositories`() {
        model.createOrUpdate(listOf(repository))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support replace or create one repository`() {
        model.createOrUpdateOne(repository)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `should find repositories`() {
        val query: Map<String, Any> = listOf(
            "language" to "Kotlin",
            "min" to 30,
            "order" to "asc",
            "page" to 1,
            "sort" to "stars"
        ).toMap()

        model.find(query = query)
            .firstOrError()
            .toObservable()
            .flatMapIterable { it }
            .filter { it.language == "Kotlin" }
            .count()
            .test()
            .assertNoErrors()
            .assertValue { it > 0 }
            .dispose()
    }

    @Test
    fun `should fail on query missing language`() {
        model.find()
            .test()
            .assertError(IllegalArgumentException::class.java)
            .dispose()
    }

    @Test
    fun `should not support contains keys`() {
        model.containsKey(repository.key)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()

        model.containsKeys(setOf(repository.key))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `should not support contains values`() {
        model.containsValue(repository)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()

        model.containsValues(listOf(repository))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support update one repository`() {
        model.updateOne(repository)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support update repositories bulk`() {
        model.update(listOf(repository))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    companion object {
        private const val MOCK_PORT = 6166
        private const val MOCK_URL = "http://localhost:${MOCK_PORT}"
    }

}
