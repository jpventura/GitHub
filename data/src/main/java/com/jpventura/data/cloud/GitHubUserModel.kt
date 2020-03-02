package com.jpventura.data.cloud

import com.jpventura.data.ktx.asBean
import com.jpventura.domain.bean.User
import com.jpventura.domain.model.VersionControlModel

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class GitHubUserModel(
    private val client: GitHubClient
) : VersionControlModel.UserModel {

    override fun clear(): Single<Int> =
        Single.error(NotImplementedError())

    override fun containsKey(key: String): Single<Boolean> =
        Single.error(NotImplementedError())

    override fun containsKeys(keys: Collection<String>): Single<Boolean> =
        Single.error(NotImplementedError())

    override fun containsValue(value: User): Completable =
        Completable.error(NotImplementedError())

    override fun containsValues(values: Collection<User>): Completable =
        Completable.error(NotImplementedError())

    override fun create(values: Collection<User>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun createOne(value: User): Single<String> =
        Single.error(NotImplementedError())

    override fun createOrUpdate(values: Collection<User>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun createOrUpdateOne(value: User): Single<String> =
        Single.error(NotImplementedError())

    override fun destroy(keys: Collection<String>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun destroyOne(key: String): Single<String> =
        Single.error(NotImplementedError())

    override fun find(keys: Collection<String>): Observable<List<User>> =
        Observable.error(NotImplementedError())

    override fun find(query: Map<String, Any>): Observable<List<User>> {
        val login = query["login"] as String?

        if (login.isNullOrBlank()) {
            return Observable.error(IllegalArgumentException("Missing query param: login"))
        }

        return Observable.error(NotImplementedError())
    }

    override fun findOne(key: String): Single<User> =
        client.getUser(key)
            .map { it.copy(key = key) }
            .map { it.asBean() }

    override fun keys(): Observable<List<String>> =
        Observable.error(NotImplementedError())

    override fun size(): Observable<Int> =
        Observable.error(NotImplementedError())

    override fun update(values: Collection<User>): Single<Int> =
        Single.error(NotImplementedError())

    override fun updateOne(value: User): Single<Int> =
        Single.error(NotImplementedError())

}
