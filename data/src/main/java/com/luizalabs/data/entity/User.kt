package com.luizalabs.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luizalabs.core.domain.bean.Bean

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    override val id: Long,

    val login: String,

    val name: String
) : Bean<Long>
