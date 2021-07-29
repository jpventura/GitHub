package com.jpventura.domain.di

import com.jpventura.core.domain.model.MemoryModel
import com.jpventura.core.domain.model.MemoryNestedModel
import com.jpventura.domain.UseCase
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.bean.User
import com.jpventura.domain.interactor.FindRepositoryIssues
import com.jpventura.domain.interactor.FindRepositoryPullRequests
import com.jpventura.domain.interactor.FindTrendingRepositories
import com.jpventura.domain.VersionControlModel
import com.jpventura.domain.bean.PullRequest
import com.jpventura.domain.model.RepositoryModel
import io.reactivex.schedulers.TestScheduler
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun domainModules() = Kodein.Module("DomainModule") {
    bind<VersionControlModel.IssueModel>() with singleton {
        object : MemoryNestedModel<String, Int, Issue>(), VersionControlModel.IssueModel {}
    }

    bind<VersionControlModel.PullModel>() with singleton {
        object : MemoryNestedModel<String, Int, PullRequest>(), VersionControlModel.PullModel {}
    }

    bind<VersionControlModel.RepositoryModel>() with singleton {
        RepositoryModel()
    }

    bind<VersionControlModel.UserModel>() with singleton {
        object : MemoryModel<String, User>(), VersionControlModel.UserModel {}
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

    bind<TestScheduler>() with singleton {
        TestScheduler()
    }
}
