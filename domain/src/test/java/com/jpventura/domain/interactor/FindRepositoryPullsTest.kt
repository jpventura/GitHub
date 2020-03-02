package com.jpventura.domain.interactor

import com.jpventura.domain.UseCases
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.kodein.domainModules
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindRepositoryPullsTest : KodeinAware {

    override val kodein = Kodein.lazy {
        import(domainModules())
    }

    private val subject = PublishSubject.create<List<Issue>>()
    private val interactor: UseCases.FindRepositoryIssues by instance()
    private val testScheduler: TestScheduler by instance()

    @Test
    fun `should find no pull requests`() {
    }

    @Test
    fun `should find pull requests`() {
    }

}
