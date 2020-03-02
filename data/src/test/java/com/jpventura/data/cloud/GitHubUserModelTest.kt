package com.jpventura.data.cloud

import com.jpventura.domain.bean.User
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

class GitHubUserModelTest {

    private val testScheduler = TestScheduler()

    private lateinit var model: VersionControlModel.UserModel

    private val user: User = user {
        avatarUrl = "https://avatars2.githubusercontent.com/u/878437?v=4"
        id = 878437L
        key = "JetBrains"
        name = "JetBrains"
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

        model = GitHubUserModel(client = GitHubClient.create(interceptors = interceptors))

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
        model.create(listOf(user))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support create one repository`() {
        model.createOne(user)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support destroy repositories`() {
        model.destroy(listOf(user.key))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support destroy one repository`() {
        model.destroyOne(user.key)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support replace or create repositories`() {
        model.createOrUpdate(listOf(user))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support replace or create one repository`() {
        model.createOrUpdateOne(user)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `should fail on query missing username`() {
        model.find()
            .test()
            .assertError(IllegalArgumentException::class.java)
            .dispose()
    }

    @Test
    fun `should find one user`() {
        model.findOne(user.key)
            .test()
            .assertComplete()
            .assertValue(user)
            .dispose()
    }

    @Test
    fun `should not support contains keys`() {
        model.containsKey(user.key)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()

        model.containsKeys(setOf(user.key))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `should not support contains values`() {
        model.containsValue(user)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()

        model.containsValues(listOf(user))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support update one repository`() {
        model.updateOne(user)
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    @Test
    fun `Should not support update repositories bulk`() {
        model.update(listOf(user))
            .test()
            .assertError(NotImplementedError::class.java)
            .dispose()
    }

    companion object {
        private const val MOCK_PORT = 6166
        private const val MOCK_URL = "http://localhost:${MOCK_PORT}"
    }

}
