package com.jpventura.github.issues

import com.jpventura.domain.bean.Issue
import com.jpventura.github.core.ui.State

interface IssuesContract {

    interface View {

        val router: Router
    }

    interface ViewModel {

        val state: State
    }

    interface Router {

        fun goToBrowser(issue: Issue)

        fun goToRepositoriesView()
    }
}
