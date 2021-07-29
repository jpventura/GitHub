package com.jpventura.domain.model

import com.jpventura.core.domain.model.MemoryModel
import com.jpventura.domain.VersionControlModel
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.bean.owner
import com.jpventura.domain.bean.repository
import com.jpventura.domain.bean.user
import io.reactivex.Observable

class RepositoryModel : MemoryModel<String, Repository>(
    model = REPOSITORIES
), VersionControlModel.RepositoryModel {

    companion object {
        private fun Observable<List<Repository>>.query(query: Map<String, Any>): Observable<List<Repository>> {
            return this.map { repositories ->
                repositories.filter { repos -> query["language"] as String == repos.language }
            }
        }
    }
}

// FIXME: Load from repositories.json samples file
val REPOSITORIES = mapOf(
    "JetBrains/kotlin" to repository {
        description = "The Kotlin Programming Language"
        forks = 3727
        fullName = "JetBrains/kotlin"
        id = 878437L
        language = "Kotlin"
        name = "kotlin"
        owner = owner {
            avatarUrl = "https://avatars2.githubusercontent.com/u/878437?v=4"
            id = 878437L
            key = "JetBrains"
            name = "JetBrains"
            reposUrl = "https://github.com/jetBrains/kotlin"
            userUrl = "https://github.com/jetBrains"
        }
        stargazersCount = 30918
        user = user {
            avatarUrl = "https://avatars2.githubusercontent.com/u/878437?v=4"
            key = "JetBrains"
            name = "JetBrains"
        }
    },
    "square/okhttp" to repository {
        description = "The Kotlin Programming Language"
        forks = 7861
        fullName = "square/okhttp"
        id = 5152285
        language = "Kotlin"
        name = "kotlin"
        owner = owner {
            avatarUrl =  "https://avatars0.githubusercontent.com/u/82592?v=4"
            id = 878437L
            key = "JetBrains"
            name = "JetBrains"
            reposUrl = "https://github.com/jetBrains/kotlin"
            userUrl = "https://github.com/jetBrains"
        }
        stargazersCount = 36080
        user = user {
            avatarUrl = "https://avatars2.githubusercontent.com/u/1833474?v=4"
            key = "square"
            name = "Square"
        }
    }
)
