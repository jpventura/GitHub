package com.jpventura.domain.bean

import java.util.Date

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

    fun build(): Issue =
        Issue(
            key = requireNotNull(number) { "Missing issue key" },
            parentKey = "${requireNotNull(repositoryId)}/${requireNotNull(number)}",
            body = requireNotNull(body) { "Missing issue body" },
            createdAt = requireNotNull(createdAt) { "Missing issue creation date" },
            id = requireNotNull(id) { "Missing issue id" },
            title = requireNotNull(title) { "Missing issue title" },
            url = requireNotNull(url) { "Missing issue url" },
            user = requireNotNull(user) { "Missing issue user" }
        )

}
