package com.jpventura.data.entity

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
        parentKey = requireNotNull(repositoryId),
        key = requireNotNull(number),

        id = requireNotNull(id),
        body = requireNotNull(body),
        createdAt = requireNotNull(createdAt),
        state = requireNotNull(state),
        title = requireNotNull(title),
        user = requireNotNull(user)
    )

}
