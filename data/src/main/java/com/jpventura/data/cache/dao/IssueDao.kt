package com.jpventura.data.cache.dao

import androidx.room.*
import com.jpventura.data.entity.Issue
import com.jpventura.data.entity.Pull
import io.reactivex.Observable
import io.reactivex.Single

//@Dao
//interface IssueDao {
//
//    @Delete
//    fun clear(repositories: List<Pull>): Single<List<String>>
//
//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    fun create(pull: Pull): Single<List<String>>
//
//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    fun createOne(repositories: List<Pull>): Single<String>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun createOrUpdate(pull: Pull): Single<List<String>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun createOrUpdateOne(pulls: List<Pull>): Single<String>
//
//    @Query("DELETE FROM pull_requests WHERE repository_id = :repositoryId")
//    fun destroy(repositoryId: String): Single<String>
//
//    @Query("DELETE FROM pull_requests WHERE id = :id AND repository_id = :repositoryId")
//    fun destroyOne(repositoryId: String, id: String)
//
//    @Query("SELECT * FROM pull_requests ORDER BY id")
//    fun find(repositoryId: String): Observable<List<Pull>>
//
//    @Query("SELECT * FROM pull_requests WHERE id = :id AND repository_id = :repositoryId")
//    fun findOne(repositoryId: String, id: Long): Single<String>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun update(pulls: List<Pull>): Single<Int>
//
//    @Query("SELECT COUNT(*) FROM pull_requests WHERE repository_id = :repositoryId")
//    fun size(repositoryId: String): Observable<Int>
//
//}


@Dao
interface IssueDao {

    @Query("DELETE FROM issues")
    fun clear(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun create(issue: Issue): Single<List<String>>
//
//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    fun createOne(repositories: List<Issue>): Single<String>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun createOrUpdate(issue: Issue): Single<List<String>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun createOrUpdateOne(issues: List<Issue>): Single<String>
//
//    @Query("DELETE FROM issues WHERE repository_id = :repositoryId")
//    fun destroy(repositoryId: String): Single<String>
//
//    @Query("DELETE FROM issues WHERE id = :id AND repository_id = :repositoryId")
//    fun destroyOne(repositoryId: String, id: String)
//
//    @Query("SELECT * FROM issues ORDER BY id")
//    fun find(repositoryId: String): Observable<List<Issue>>
//
//    @Query("SELECT * FROM issues WHERE id = :id AND repository_id = :repositoryId")
//    fun findOne(repositoryId: String, id: Long): Single<String>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun update(issues: List<Issue>): Single<Int>
//
//    @Query("SELECT COUNT(*) FROM issues WHERE repository_id = :repositoryId")
//    fun size(repositoryId: String): Observable<Int>

}
