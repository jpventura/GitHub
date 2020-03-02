package com.jpventura.github.app.kodein

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jpventura.data.BuildConfig
import com.jpventura.data.RepositoryRepository
import com.jpventura.data.cache.RepositoryMemoryModel
import com.jpventura.data.cloud.GitHubBasicInterceptor
import com.jpventura.data.cloud.GitHubClient
import com.jpventura.data.cloud.GitHubIssueModel
import com.jpventura.data.cloud.GitHubPullModel
import com.jpventura.data.cloud.GitHubRepositoryModel
import com.jpventura.data.cloud.GitHubUserModel
import com.jpventura.domain.UseCases
import com.jpventura.domain.interactor.FindRepositoryIssues
import com.jpventura.domain.interactor.FindRepositoryPulls
import com.jpventura.domain.interactor.FindTrendingRepositories
import com.jpventura.domain.model.VersionControlModel
import com.jpventura.github.core.data.cloud.TimberInterceptorLogger
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun applicationModule(context: Context) = Kodein.Module("applicationModule") {
    bind<Context>() with provider {
        context
    }
    import(modelModules())
    import(useCaseModules())
}

private fun modelModules() = Kodein.Module("modelModule") {
    bind<Gson>() with singleton {
        GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
    }

    bind<GitHubClient>() with singleton {
        val timber = HttpLoggingInterceptor(logger = TimberInterceptorLogger("GitHubClient"))
        val stetho = GitHubBasicInterceptor()

        val interceptors: List<Interceptor> = if (BuildConfig.DEBUG) {
            listOf<Interceptor>(stetho, timber)
        } else {
            emptyList()
        }

        GitHubClient.create(
            gson = instance(),
            baseUrl = BuildConfig.GITHUB_BASE_URL,
            interceptors = interceptors
        )
    }

    bind<VersionControlModel.IssueModel>() with singleton {
        GitHubIssueModel(instance())
    }

    bind<VersionControlModel.PullModel>() with singleton {
        GitHubPullModel(instance())
    }

    bind<VersionControlModel.RepositoryModel>(tag = "cache") with singleton {
        RepositoryMemoryModel()
    }

    bind<VersionControlModel.RepositoryModel>(tag = "cloud") with singleton {
        GitHubRepositoryModel(instance())
    }

    bind<VersionControlModel.RepositoryModel>() with singleton {
        RepositoryRepository(instance(tag = "cache"), instance(tag = "cloud"))
    }

    bind<VersionControlModel.UserModel>() with singleton {
        GitHubUserModel(instance())
    }
}

private fun useCaseModules() = Kodein.Module("useCaseModule") {
    bind<UseCases.FindTrendingRepositories>() with singleton {
        FindTrendingRepositories(instance())
    }
    bind<UseCases.FindRepositoryIssues>() with singleton {
        FindRepositoryIssues(instance(), instance())
    }
    bind<UseCases.FindRepositoryPulls>() with singleton {
        FindRepositoryPulls(instance())
    }
}
