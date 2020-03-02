package com.jpventura.data.entity

import androidx.room.*
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jpventura.core.domain.bean.NestedBean
import java.util.*

@Entity(
    tableName = "pull_requests",
    indices = [
        Index("user_id"),
        Index("repository_id")
    ]
)
data class Pull(
    @SerializedName("number")
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val key: Int,

    @Expose(serialize = false, deserialize = false)
    @ColumnInfo(name = "repository_id")
    override val parentKey: String,

    val body: String,

    @SerializedName("created_at")
    val createdAt: Date,

    @ColumnInfo(name = "pull_id")
    val id: Long,

    val state: String,

    val title: String,

    @ColumnInfo(name = "user_id")
    val user: User
) : NestedBean<String, Int>
