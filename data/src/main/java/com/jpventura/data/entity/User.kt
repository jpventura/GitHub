package com.jpventura.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jpventura.core.domain.bean.Bean

@Entity(
    tableName = "users",
    indices = [
        Index("id")
    ]
)
data class User(
    @PrimaryKey
    @SerializedName("login")
    @ColumnInfo(name = "login")
    override val key: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    val id: Long,

    val name: String? = ""
) : Bean<String>
