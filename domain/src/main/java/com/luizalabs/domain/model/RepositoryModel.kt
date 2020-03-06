package com.luizalabs.domain.model

import com.luizalabs.core.domain.model.PersistedModel
import com.luizalabs.domain.bean.Repository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryModel : PersistedModel<String, Repository> {

    override fun clear(): Single<Int> = TODO()

    override fun containsKey(key: String): Completable = TODO()

    override fun containsKeys(keys: Set<String>): Completable = TODO()

    override fun containsValue(value: Repository): Completable = TODO()

    override fun containsValues(values: Collection<Repository>): Completable = TODO()

    override fun create(values: Collection<Repository>): Single<List<String>> = TODO()

    override fun createOne(value: Repository): Single<String> = TODO()

    override fun createOrUpdate(values: Collection<Repository>): Single<List<String>> = TODO()

    override fun createOrUpdateOne(value: Repository): Single<String> = TODO()

    override fun destroy(keys: Collection<String>): Single<List<String>> = TODO()

    override fun destroyOne(key: String): Single<String> = TODO()

    override fun find(): Observable<List<Repository>> = TODO()

    override fun find(keys: Set<String>): Observable<List<Repository>> = TODO()

    override fun findOne(key: String): Single<Repository> = TODO()

    override fun keys(): Observable<List<String>> = TODO()

    override fun size(): Observable<Int> = TODO()

    override fun update(values: Collection<Repository>): Single<Int> = TODO()

    override fun updateOne(value: Repository): Single<Int> = TODO()

}
