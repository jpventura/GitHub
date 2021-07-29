package com.jpventura.domain.interactor

import com.jpventura.domain.UseCase
import com.jpventura.domain.model.REPOSITORIES
import com.jpventura.domain.model.IssueModel
import com.jpventura.domain.model.PullRequestModel
import com.jpventura.domain.model.RepositoryModel
import com.jpventura.domain.model.UserModel
import com.jpventura.domain.VersionControlModel
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

abstract class UseCaseTest : KodeinAware {
    val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        repositories.createOrUpdate(REPOSITORIES.values)
    }

    @After
    fun tearDown() {
        repositories.clear()
        RxJavaPlugins.setIoSchedulerHandler { null }
        RxJavaPlugins.setComputationSchedulerHandler { null }
    }

    override val kodein = Kodein.lazy {
        bind<VersionControlModel.IssueModel>() with singleton {
            IssueModel()
        }

        bind<VersionControlModel.PullModel>() with singleton {
            PullRequestModel()
        }

        bind<VersionControlModel.RepositoryModel>() with singleton {
            RepositoryModel()
        }

        bind<VersionControlModel.UserModel>() with singleton {
            UserModel()
        }

        bind<UseCase.FindRepositoryIssues>() with singleton {
            FindRepositoryIssues(instance(), instance())
        }

        bind<UseCase.FindRepositoryPullRequests>() with singleton {
            FindRepositoryPullRequests(instance())
        }

        bind<UseCase.FindTrendingRepositories>() with singleton {
            FindTrendingRepositories(instance())
        }
    }

    val issues: VersionControlModel.IssueModel by instance()
    val pullRequests: VersionControlModel.IssueModel by instance()
    val repositories: VersionControlModel.RepositoryModel by instance()
}
