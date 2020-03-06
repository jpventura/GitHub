package com.luizalabs.domain.bean

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val id: Long,
    val login: String,
    val name: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("user")
    val userUrl: String
)
