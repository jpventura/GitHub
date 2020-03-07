package com.luizalabs.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.luizalabs.core.domain.bean.Bean

@Entity(tableName = "repositories")
data class Repository(
    @PrimaryKey
    override val id: Long,

    val description: String,

    val forks: Int,

    val fullName: String,

    val language: String,

    val name: String,

    @SerializedName("stargazers_count")
    @ColumnInfo(name = "stargazers_count")
    val stargazersCount: Int
) : Bean<Long>
