package com.jpventura.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jpventura.core.domain.bean.Bean

@Entity(
    tableName = "owners",
    indices = [
        Index("id")
    ]
)
data class Owner(
    @PrimaryKey
    @ColumnInfo(name = "owner_id")
    @SerializedName("login")
    override val key: String,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val avatarUrl: String,

    val id: Long,

    val name: String? = "",

    @ColumnInfo(name = "repos_url")
    @SerializedName("repos_url")
    val reposUrl: String,

    @ColumnInfo(name = "user_url")
    @SerializedName("user_url")
    val userUrl: String?
) : Bean<String>
