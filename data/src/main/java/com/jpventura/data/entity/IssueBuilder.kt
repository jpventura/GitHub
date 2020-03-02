package com.jpventura.data.entity

import java.util.*

fun issue(block: IssueBuilder.() -> Unit): Issue = IssueBuilder()
    .apply(block)
    .build()

class IssueBuilder {
    var body: String? = null
    var createdAt: Date? = null
    var id: Long? = null
    var number: Int? = null
    var repositoryId: String? = null
    var title: String? = null
    var url: String? = null
    var user: User? = null

    fun build(): Issue = Issue(
        key = requireNotNull(number),
        body = requireNotNull(body),
        createdAt = requireNotNull(createdAt),
        id = requireNotNull(id),
        parentKey = requireNotNull(repositoryId),
        title = requireNotNull(title),
        url = requireNotNull(url),
        user = requireNotNull(user)
    )

}
