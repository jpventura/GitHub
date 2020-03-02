package com.jpventura.github.main

import com.jpventura.domain.bean.Repository

interface MainContract {

    interface Router {

        fun goToIssuesView(repository: Repository)

        fun goToRepositoriesView()
    }
}
