package com.jpventura.domain.bean

fun user(block: UserBuilder.() -> Unit): User = UserBuilder()
    .apply(block)
    .build()

class UserBuilder {
    var avatarUrl: String? = null
    var id: Long? = null
    var key: String? = null
    var name: String? = null

    fun build(): User = User(
        avatarUrl = requireNotNull(avatarUrl) { "Missing user property: avatarUrl "},
        id = requireNotNull(id) { "Missing user property: id "},
        key = requireNotNull(key) { "Missing user property: key "},
        name = name ?: ""
    )
}
