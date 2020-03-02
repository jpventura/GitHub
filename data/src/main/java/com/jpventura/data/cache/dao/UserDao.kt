package com.jpventura.data.cache.dao

import androidx.room.*
import com.jpventura.data.entity.User
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("DELETE FROM users")
    fun clear(): Single<Int>

    @Query("SELECT EXISTS(SELECT 1 FROM users ORDER BY login = :key)")
    fun containsKey(key: String): Single<Boolean>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun create(values: List<User>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createOne(values: User): Single<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun createOrUpdate(repository: User): Single<Int>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun createOrUpdateOne(values: List<User>): Single<Int>

    @Query("DELETE FROM users WHERE login = :id")
    fun destroy(id: String): Single<Int>

    @Delete
    fun destroy(values: List<User>): Single<Int>

    @Delete
    fun destroyOne(values: User): Single<Int>

    @Query("SELECT * FROM users ORDER BY login")
    fun find(): Observable<List<User>>

    @Query("SELECT * FROM users WHERE login IN (:values) ORDER BY login")
    fun find(vararg values: String): Observable<List<User>>

    @Query("SELECT * FROM users WHERE id = :value")
    fun findOne(value: String): Single<User>

    @Query("SELECT login FROM users ORDER BY login")
    fun keys(): Observable<List<String>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(values: List<User>): Single<Int>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User): Single<Int>

    @Query("SELECT COUNT(*) FROM users")
    fun size(): Observable<Int>

}
