package com.luizalabs.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories")
data class Repository(
    @PrimaryKey
    val id: Long,

    val description: String,

    val forks: Int,

    val fullName: String,

    val language: String,

    val name: String,

    @SerializedName("stargazers_count")
    @ColumnInfo(name = "stargazers_count")
    val stargazersCount: Int
)
