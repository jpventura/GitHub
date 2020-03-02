package com.jpventura.data.entity

import androidx.room.*
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jpventura.core.domain.bean.Bean

@Entity(
    tableName = "repositories",
    indices = [
        Index("user_id"),
        Index("repository_id")
    ]
)
data class Repository(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("full_name")
    override val key: String,

    val description: String?,

    val forks: Int,

    @SerializedName("repository_id")
    val id: Long,

    val language: String,

    val name: String,

    @Embedded
    val owner: Owner,

    @ColumnInfo(name = "user_id")
    @Expose(deserialize = false, serialize = false)
    val ownerId: String? = null,

    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    val stargazersCount: Int
) : Bean<String>
