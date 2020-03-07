package com.luizalabs.data.ktx

import com.luizalabs.data.entity.User
import com.luizalabs.domain.bean.User as UserBean
import com.luizalabs.domain.bean.user as userBean

fun user(block: UserBuilder.() -> Unit): User = UserBuilder()
    .apply(block)
    .build()

fun User.asBean(): UserBean = userBean {
    id = this@asBean.id
    login = this@asBean.login
    name = this@asBean.name
}

fun UserBean.asEntity(): User = user {
    id = this@asEntity.id
    login = this@asEntity.login
    name = this@asEntity.name
}

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
    