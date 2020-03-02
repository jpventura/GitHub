package com.jpventura.domain.interactor

import com.jpventura.domain.UseCases
import com.jpventura.domain.model.REPOSITORIES
import com.jpventura.domain.model.IssueModel
import com.jpventura.domain.model.PullModel
import com.jpventura.domain.model.RepositoryModel
import com.jpventura.domain.model.UserModel
import com.jpventura.domain.model.VersionControlModel
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
            PullModel()
        }

        bind<VersionControlModel.RepositoryModel>() with singleton {
            RepositoryModel()
        }

        bind<VersionControlModel.UserModel>() with singleton {
            UserModel()
        }

        bind<UseCases.FindRepositoryIssues>() with singleton {
            FindRepositoryIssues(instance(), instance())
        }

        bind<UseCases.FindRepositoryPulls>() with singleton {
            FindRepositoryPulls(instance())
        }

        bind<UseCases.FindTrendingRepositories>() with singleton {
            FindTrendingRepositories(instance())
        }
    }

    val issues: VersionControlModel.IssueModel by instance()
    val pulls: VersionControlModel.IssueModel by instance()
    val repositories: VersionControlModel.RepositoryModel by instance()

}
