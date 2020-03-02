package com.jpventura.domain.bean

import java.util.*

fun pullRequest(block: PullRequestBuilder.() -> Unit): Pull = PullRequestBuilder()
    .apply(block)
    .build()

class PullRequestBuilder {
    var body: String? = null
    var createdAt: Date? = null
    var id: Long? = null
    var number: Int? = null
    var repositoryId: String? = null
    var state: String? = null
    var title: String? = null
    var user: User? = null

    fun build(): Pull = Pull(
        body = requireNotNull(body) { "Missing pull request property: body" },
        createdAt = requireNotNull(createdAt) { "Missing pull request property: created_at" },
        id = requireNotNull(id) { "Missing pull request property: id" },
        key = requireNotNull(number) { "Missing pull request property: key" },
        number = requireNotNull(number) { "Missing pull request property: number" },
        parentKey = requireNotNull(repositoryId) { "Missing pull request property: repository key" },
        state = requireNotNull(state) { "Missing pull request property: state" },
        title = requireNotNull(title) { "Missing pull request property: title" },
        user = requireNotNull(user) { "Missing pull request property: user" }
    )

}
