package com.jpventura.data.cache.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jpventura.data.entity.*

@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RoomVersionControl : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var instance: RoomVersionControl? = null

        fun getInstance(context: Context): RoomVersionControl {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): RoomVersionControl {
            val databaseName = "${context.packageName}.db"
            return Room.databaseBuilder(context, RoomVersionControl::class.java, databaseName)
                .build()
        }

    }

}
