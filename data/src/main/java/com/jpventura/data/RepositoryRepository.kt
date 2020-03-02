package com.jpventura.data

import com.jpventura.domain.bean.Repository
import com.jpventura.domain.model.VersionControlModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryRepository(
    val cache: VersionControlModel.RepositoryModel,
    val cloud: VersionControlModel.RepositoryModel
) : VersionControlModel.RepositoryModel {

    override fun clear(): Single<Int> = cache.clear()

    override fun create(values: Collection<Repository>): Single<List<String>> = cache.create(values)

    override fun createOne(value: Repository): Single<String> = cache.createOne(value)

    override fun createOrUpdate(values: Collection<Repository>): Single<List<String>> =
        cache.createOrUpdate(values)

    override fun createOrUpdateOne(value: Repository): Single<String> =
        cache.createOrUpdateOne(value)

    override fun destroy(keys: Collection<String>): Single<List<String>> =
        cache.destroy(keys)

    override fun destroyOne(key: String): Single<String> =
        cache.destroyOne(key)

    override fun update(values: Collection<Repository>): Single<Int> =
        cache.update(values)

    override fun updateOne(value: Repository): Single<Int> =
        cache.updateOne(value)

    override fun containsKey(key: String): Single<Boolean> = cache.containsKey(key)

    override fun containsKeys(keys: Collection<String>): Single<Boolean> = cache.containsKeys(keys)

    override fun containsValue(value: Repository): Completable = cache.containsValue(value)

    override fun containsValues(values: Collection<Repository>): Completable =
        cache.containsValues(values)

    override fun find(keys: Collection<String>): Observable<List<Repository>> = cache.find(keys)

    override fun find(query: Map<String, Any>): Observable<List<Repository>> {
        return cloud.find(query)
            .flatMapSingle {
                cache.createOrUpdate(it)
            }
            .flatMap {
                cache.find(query)
            }
            .onErrorResumeNext(cache.find(query))
            .map {
                it
            }
    }

    override fun findOne(key: String): Single<Repository> = cache.findOne(key)

    override fun keys(): Observable<List<String>> = cache.keys()

    override fun size(): Observable<Int> = cache.size()

}
