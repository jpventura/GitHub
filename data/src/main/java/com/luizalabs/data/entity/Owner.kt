package com.luizalabs.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "owners")
data class Owner(
    @PrimaryKey
    val id: Long,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    val login: String,

    val name: String,

    @SerializedName("repos_url")
    @ColumnInfo(name = "repos_url")
    val reposUrl: String,

    @SerializedName("user_url")
    @ColumnInfo(name = "user_url")
    val userUrl: String
)
