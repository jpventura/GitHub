package com.luizalabs.domain.model

import com.luizalabs.core.domain.model.PersistedModel
import com.luizalabs.domain.bean.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserModel : PersistedModel<String, User> {

    override fun clear(): Single<Int> = TODO()

    override fun createOne(value: User): Single<String> = TODO()

    override fun create(values: Collection<User>): Single<List<String>> = TODO()

    override fun createOrUpdate(values: Collection<User>): Single<List<String>> = TODO()

    override fun createOrUpdateOne(value: User): Single<String> = TODO()

    override fun containsKey(key: String): Completable = TODO()

    override fun containsKeys(keys: Set<String>): Completable = TODO()

    override fun containsValue(value: User): Completable = TODO()

    override fun containsValues(values: Collection<User>): Completable = TODO()

    override fun destroy(keys: Collection<String>): Single<List<String>> = TODO()

    override fun destroyOne(key: String): Single<String> = TODO()

    override fun find(): Observable<List<User>> = TODO()

    override fun find(keys: Set<String>): Observable<List<User>> = TODO()

    override fun findOne(key: String): Single<User> = TODO()

    override fun keys(): Observable<List<String>> = TODO()

    override fun size(): Observable<Int> = TODO()

    override fun update(values: Collection<User>): Single<Int> = TODO()

    override fun updateOne(value: User): Single<Int> = TODO()

}
