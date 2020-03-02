package com.jpventura.data.cache.dao

import androidx.room.*
import com.jpventura.data.entity.Repository
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface RepositoryDao {

    @Delete
    fun clear(repositories: List<Repository>): Single<List<String>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun create(repository: Repository): Single<List<String>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createOne(repositories: List<Repository>): Single<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createOrUpdate(repository: Repository): Single<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createOrUpdateOne(repositories: List<Repository>): Single<String>

    @Query("DELETE FROM repositories WHERE id = :id")
    fun destroy(id: String): Single<String>

    @Delete
    fun destroy(repositories: List<Repository>): Single<List<String>>

    fun destroyOne(repositories: Repository)

    @Query("SELECT * FROM repositories ORDER BY id")
    fun find(): Observable<List<Repository>>

    @Query("SELECT * FROM repositories WHERE id IN (:repositories)")
    fun find(vararg repositories: String): Observable<List<Repository>>

    @Query("SELECT * FROM repositories WHERE id = :id")
    fun findOne(id: Long): Single<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(repositories: List<Repository>): Single<Int>

    @Query("SELECT COUNT(*) FROM repositories")
    fun size(): Observable<Int>

}
