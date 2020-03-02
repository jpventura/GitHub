package com.jpventura.data.entity

fun owner(block: OwnerBuilder.() -> Unit): Owner = OwnerBuilder()
    .apply(block)
    .build()

class OwnerBuilder {
    var avatarUrl: String? = null
    var id: Long? = null
    var key: String? = null
    var name: String? = null
    var reposUrl: String? = null
    var userUrl: String? = null

    fun build(): Owner = Owner(
        avatarUrl = requireNotNull(avatarUrl),
        id = requireNotNull(id),
        key = requireNotNull(key),
        name = requireNotNull(name),
        reposUrl = requireNotNull(reposUrl),
        userUrl = this@OwnerBuilder.userUrl
    )

}
