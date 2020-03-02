package com.jpventura.data.cloud

import com.jpventura.data.BuildConfig
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class GitHubBasicInterceptor(
    private val username: String = BuildConfig.GITHUB_USERNAME,
    private val password: String = BuildConfig.GITHUB_PASSWORD
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val credentials = Credentials.basic(username = username, password = password)

        return chain.proceed(
            request
                .newBuilder()
                .header(name = "Authorization", value = credentials)
                .method(request.method, request.body)
                .build()
        )
    }

}
