package com.jpventura.data.entity

import androidx.room.*
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jpventura.core.domain.bean.NestedBean
import java.util.*

@Entity(
    tableName = "issues",
    indices = [
        Index("user_id"),
        Index("repository_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = Repository::class,
            parentColumns = ["repository_id"],
            childColumns = ["repository_id"]
        )
    ]
)
data class Issue(
    @PrimaryKey
    @SerializedName("number")
    @ColumnInfo(name = "id")
    override val key: Int,

    @Expose(serialize = false, deserialize = false)
    @ColumnInfo(name = "repository_id")
    override val parentKey: String,

    val body: String? = "",

    @SerializedName("created_at")
    val createdAt: Date,

    @ColumnInfo(name = "issue_id")
    val id: Long,

    val title: String,

    val url: String,

    @SerializedName("user")
    @Embedded
    val user: User

) : NestedBean<String, Int>
