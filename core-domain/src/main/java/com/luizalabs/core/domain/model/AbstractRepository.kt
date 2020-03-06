package com.luizalabs.core.domain.model

import io.reactivex.Observable
import io.reactivex.Single

private class AbstractRepository<K, V>(
    private val cache: PersistedModel<K, V>,
    private val cloud: PersistedModel<K, V>
) {

    enum class CachePolicy {
        CACHE_FIRST(),
        CACHE_ONLY(),
        NETWORK_FIRST(),
        NETWORK_ONLY()
    }

//    override fun clear(): Single<Int> = TODO()
//
//    override fun createOne(value: V): Single<K> = TODO()
//
//    override fun create(values: Collection<V>): Single<List<V>> = TODO()
//
//    override fun destroy(values: Collection<K>): Single<Int> = TODO()
//
//    override fun destroyOne(value: V): Single<Int> = TODO()
//
//    override fun containsKey(key: K): Single<Boolean> = TODO()
//
//    override fun containsValue(value: V): Single<Boolean> = TODO()
//
//    override fun entries(): Observable<List<Map.Entry<K, V>>> = TODO()
//
//    override fun find(): Observable<List<V>> = TODO()
//
//    override fun findOne(id: K): Observable<V> = TODO()
//
//    override fun keys(): Observable<List<K>> = TODO()
//
//    override fun size(): Observable<Int> = TODO()
//
//    override fun update(values: Collection<V>): Single<List<V>> = TODO()
//
//    override fun updateOne(value: V): Single<K> = TODO()
//
//    fun cachePolicy(policy: CachePolicy): AbstractRepository<K, V> = TODO()
//
//    fun expireAfter(expireTimeout: Int, timeUnit: Int): AbstractRepository<K, V> = TODO()
//
//    inner class Builder<K, V : Bean> {
//
//        lateinit var cache: PersistedModel<K, V>
//
//        lateinit var cachePolicy: CachePolicy
//
//        lateinit var cloud: PersistedModel<K, V>
//
//        fun setCloudModel(cloud: PersistedModel<K, V>): Builder<K, V> {
//            this.cloud = cloud
//            return this
//        }
//
//        fun setCacheModel(cache: PersistedModel<K, V>): Builder<K, V> {
//            this.cache = cache
//            return this
//        }
//
//        fun setCachePolicy(cachePolicy: CachePolicy): Builder<K, V> {
//            this.cachePolicy = cachePolicy
//            return this
//        }
//
//        fun build(): PersistedModel<K, V> = AbstractRepository(cache = cache, cloud = cloud)
//    }
//
//    fun <K, V : Bean> repository(block: (Builder<K, V>) -> Unit): PersistedModel<K, V> =
//        Builder<K, V>().apply(block).build()

}
