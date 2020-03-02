package com.jpventura.data.cache.dao

import androidx.room.TypeConverter
import com.jpventura.data.entity.Owner
import com.jpventura.data.entity.User

class Converters {

    @TypeConverter
    fun owner(owner: Owner): String = owner.key

    @TypeConverter
    fun user(user: User): String = user.key

}