package com.jpventura.data.cloud

import com.google.gson.Gson
import com.jpventura.data.BuildConfig
import com.jpventura.data.BuildConfig.GITHUB_BASE_URL
import com.jpventura.data.entity.Issue
import com.jpventura.data.entity.Pull
import com.jpventura.data.entity.Repository
import com.jpventura.data.entity.User
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubClient {

    @GET("search/repositories")
    fun getRepositories(
        @Query("q") language: String,
        @Query("order") page: Int? = 1,
        @Query("page") order: String? = null,
        @Query("sort") sort: String = "stars"
    ): Single<Response<Repository>>

    @GET("repos/{userId}/{repositoryId}")
    fun getRepositoryById(
        @Path("userId") userId: String,
        @Path("repositoryId") repositoryId: String
    ): Single<Repository>

    @GET("repos/{user}/{repository}/issues")
    fun getRepositoryIssues(
        @Path("user") user: String,
        @Path("repository") repository: String
    ): Single<List<Issue>>

    @GET("repos/{user}/{repository}/issues/{number}")
    fun getRepositoryIssue(
        @Path("user") user: String,
        @Path("repository") repository: String,
        @Path("number") number: Int
    ): Single<Issue>

    @GET("repos/{user}/{repository}/pulls")
    fun getRepositoryPullRequests(
        @Path("user") user: String,
        @Path("repository") repository: String
    ): Single<List<Pull>>

    @GET("users/{user}")
    fun getUser(
        @Path("user") userId: String
    ): Single<User>

    companion object {
        private const val TAG = "GitHubClient"

        fun create(
            gson: Gson = Gson(),
            baseUrl: String = GITHUB_BASE_URL,
            interceptors: List<Interceptor> = emptyList()
        ): GitHubClient {
            val builder = OkHttpClient.Builder()

            if (interceptors.isEmpty() && BuildConfig.DEBUG) {
                // FIXME: Inject interceptor with Stetho and Timber
                builder.addInterceptor(HttpLoggingInterceptor(
                    logger = HttpLoggingInterceptor.Logger.DEFAULT
                ))
            }

            interceptors.forEach {
                builder.addInterceptor(it)
            }

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GitHubClient::class.java)
        }

    }

}
