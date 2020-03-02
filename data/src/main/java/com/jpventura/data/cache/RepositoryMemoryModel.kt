package com.jpventura.data.cache

import com.jpventura.domain.bean.Repository
import com.jpventura.domain.model.VersionControlModel.RepositoryModel
import io.reactivex.Observable
import kotlin.math.min

class RepositoryMemoryModel : MemoryModel<String, Repository>(), RepositoryModel {

    override fun find(query: Map<String, Any>): Observable<List<Repository>> {
        val select = query.toMutableMap().apply {
            set("language", query["language"] as? String ?: "")
            set("min", min(query["min"] as? Long ?: model.size.toLong(), model.size.toLong()))
            set("order", query["order"] as? String ?: "desc")
            set("sort", query["sort"] as? String ?: "stars")
        }

        return super.find(select)
            .map {
                it.query(query)
            }
    }
}

private fun List<Repository>.query(query: Map<String, Any>): List<Repository> {
    val min = min(query["min"] as? Int ?: size, size)

    val repositories = filter {
        it.language == query["language"]
    }.sortedBy {
        it.stargazersCount
    }.asReversed()

    return repositories.subList(0, min)
}
