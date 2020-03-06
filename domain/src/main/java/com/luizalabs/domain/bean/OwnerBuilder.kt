package com.luizalabs.domain.bean

fun owner(block: OwnerBuilder.() -> Unit): Owner = OwnerBuilder()
    .apply(block)
    .build()

class OwnerBuilder {
    var avatarUrl: String? = null
    var id: Long? = null
    var login: String? = null
    var name: String? = null
    var reposUrl: String? = null
    var userUrl: String? = null

    fun build(): Owner = Owner(
        avatarUrl = requireNotNull(avatarUrl),
        id = requireNotNull(id),
        login = requireNotNull(login),
        name = requireNotNull(name),
        reposUrl = requireNotNull(reposUrl),
        userUrl = requireNotNull(userUrl)
    )

}
