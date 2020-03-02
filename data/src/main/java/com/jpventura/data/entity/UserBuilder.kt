package com.jpventura.data.entity

fun user(block: UserBuilder.() -> Unit): User = UserBuilder()
    .apply(block)
    .build()

class UserBuilder {
    var avatarUrl: String? = null
    var id: Long? = null
    var key: String? = null
    var name: String? = null

    fun build(): User = User(
        avatarUrl = requireNotNull(avatarUrl),
        id = requireNotNull(id),
        key = requireNotNull(key),
        name = requireNotNull(name)
    )

}
