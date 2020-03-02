package com.jpventura.github.repositories

import com.jpventura.domain.bean.Repository
import com.jpventura.github.core.ui.State

interface RepositoriesContract {

    interface View {

        fun goToIssues(repository: Repository)
    }

    interface ViewModel {

        val state: State
    }
}
