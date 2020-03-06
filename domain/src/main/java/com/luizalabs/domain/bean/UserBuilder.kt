package com.luizalabs.domain.bean

fun user(block: UserBuilder.() -> Unit): User = UserBuilder()
    .apply(block)
    .build()

class UserBuilder {
    var id: Long? = null
    var login: String? = null
    var name: String? = null

    fun build(): User = User(
        id = requireNotNull(id),
        login = requireNotNull(login),
        name = requireNotNull(name)
    )

}
