package com.luizalabs.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issues")
data class Issue(
    @PrimaryKey
    val id: Int,

    val description: String
)
