package com.jpventura.data.cloud

import com.google.gson.GsonBuilder
import com.jpventura.data.BuildConfig
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class GitHubClientTest {
    private val testScheduler = TestScheduler()

    private val interceptors = listOf(object : Interceptor {
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

    private val bridge: GitHubClient = GitHubClient.create(
        gson = GsonBuilder().create(),
        baseUrl = BuildConfig.GITHUB_BASE_URL,
        interceptors = interceptors
    )

    @Before
    fun setUp() {
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.setComputationSchedulerHandler(null)
        RxJavaPlugins.setComputationSchedulerHandler { null }
    }

    @Test
    fun `should find kotlin repositories`() {
        bridge.getRepositories(language = "Kotlin", page = 1, sort = "stars")
            .test()
            .assertValue {
                it.items.isNotEmpty ()
            }
            .assertComplete()
            .dispose()
    }

    @Test
    fun `should find kotlin repository issues`() {
        bridge.getRepositoryIssues(repository = "kotlin", user = "JetBrains")
            .test()
            .assertValue {
                it.isNotEmpty ()
            }
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `should find kotlin repository pull requests`() {
        bridge.getRepositoryPullRequests(repository = "kotlin", user = "JetBrains")
            .test()
            .assertValue {
                it.isNotEmpty()
            }
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `should not find one user`() {
        bridge.getUser(userId = "13205aad-a484-4938-ac81-2929159c72ec")
            .test()
            .assertError(HttpException::class.java)
            .dispose()
    }
}
