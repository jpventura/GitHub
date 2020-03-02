package com.jpventura.data.cloud

import com.jpventura.core.domain.bean.Bean
import com.jpventura.domain.bean.Pull
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import com.jpventura.data.ktx.asBean
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.model.VersionControlModel

class GitHubPullModel(
    private val client: GitHubClient
) : VersionControlModel.PullModel {

    override fun clear(): Single<Int> = Single.error(NotImplementedError())

    override fun containsKey(key: String): Single<Boolean> =
        Single.error(NotImplementedError())

    override fun containsKeys(keys: Collection<String>): Single<Boolean> =
        Single.error(NotImplementedError())

    override fun containsValue(value: Pull): Completable =
        Completable.error(NotImplementedError())

    override fun containsValues(values: Collection<Pull>): Completable =
        Completable.error(NotImplementedError())

    override fun create(values: Collection<Pull>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun createOne(value: Pull): Single<String> =
        Single.error(NotImplementedError())

    override fun destroy(keys: Collection<String>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun destroyOne(key: String): Single<String> =
        Single.error(NotImplementedError())

    override fun find(query: Map<String, Any>): Observable<List<Pull>> =
        Observable.error(NotImplementedError())

    override fun find(keys: Collection<String>): Observable<List<Pull>> =
        Observable.error(NotImplementedError())

    override fun <U : Bean<String>> find(parent: U): Observable<List<Pull>> {
        return (parent as Repository).let { repository ->
            client.getRepositoryPullRequests(
                user = repository.owner.key,
                repository = repository.name
            ).toObservable()
                .flatMapIterable { it }
                .map {
                    it.asBean(repository = repository)
                }
                .toList()
                .toObservable()
        }
    }

    override fun findOne(key: String): Single<Pull> =
        Single.error(NotImplementedError())

    override fun keys(): Observable<List<String>> =
        Observable.error(NotImplementedError())

    override fun size(): Observable<Int> = Observable.error(NotImplementedError())

    override fun update(values: Collection<Pull>): Single<Int> =
        Single.error(NotImplementedError())

    override fun updateOne(value: Pull): Single<Int> =
        Single.error(NotImplementedError())

    override fun createOrUpdate(values: Collection<Pull>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun createOrUpdateOne(value: Pull): Single<String> =
        Single.error(NotImplementedError())

}
