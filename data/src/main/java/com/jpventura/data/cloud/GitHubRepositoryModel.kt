package com.jpventura.data.cloud

import com.jpventura.data.ktx.asBean
import com.jpventura.data.ktx.asUser
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.model.VersionControlModel
import com.jpventura.data.entity.Repository as RepositoryEntity
import com.jpventura.data.entity.User as UserEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit
import kotlin.math.max
import kotlin.math.truncate

class GitHubRepositoryModel(
    val client: GitHubClient
) : VersionControlModel.RepositoryModel {

    override fun clear(): Single<Int> =
        Single.error(NotImplementedError())

    override fun containsKey(key: String): Single<Boolean> =
        Single.error(NotImplementedError())

    override fun containsKeys(keys: Collection<String>): Single<Boolean> =
        Single.error(NotImplementedError())

    override fun containsValue(value: Repository): Completable =
        Completable.error(NotImplementedError())

    override fun containsValues(values: Collection<Repository>): Completable =
        Completable.error(NotImplementedError())

    override fun create(values: Collection<Repository>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun createOne(value: Repository): Single<String> =
        Single.error(NotImplementedError())

    override fun createOrUpdate(values: Collection<Repository>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun createOrUpdateOne(value: Repository): Single<String> =
        Single.error(NotImplementedError())

    override fun destroy(keys: Collection<String>): Single<List<String>> =
        Single.error(NotImplementedError())

    override fun destroyOne(key: String): Single<String> =
        Single.error(NotImplementedError())

    override fun find(keys: Collection<String>): Observable<List<Repository>> =
        Observable.error(NotImplementedError())

    override fun find(query: Map<String, Any>): Observable<List<Repository>> {
        val min = max(query["min"] as? Int ?: PAGE_SIZE, PAGE_SIZE)
        val pages = truncate(min.toDouble() / PAGE_SIZE).toInt()
        return find(query, pages)
    }

    override fun findOne(key: String): Single<Repository> {
        val parent = key.split("/")
        val user = parent[0]
        val repository = parent[1]

        return client.getRepositoryById(user, repository)
            .flatMap {
                copyUserIntoRepository(it)
            }
    }

    override fun keys(): Observable<List<String>> =
        Observable.error(NotImplementedError())

    override fun size(): Observable<Int> =
        Observable.error(NotImplementedError())

    override fun update(values: Collection<Repository>): Single<Int> =
        Single.error(NotImplementedError())

    override fun updateOne(value: Repository): Single<Int> =
        Single.error(NotImplementedError())

    private fun find(query: Map<String, Any>, pages: Int): Observable<List<Repository>> {
        // FIXME: Downloading elements already present at cache
        // This was only done in order to keep the repository model contract

        val queries: List<Observable<List<Repository>>> = ((1..pages).map {
            if ((query["language"] as? String).isNullOrBlank()) {
                return Observable.error(IllegalArgumentException("Missing parameter: language"))
            }

            val language = query["language"] as String
            val order = (query["order"] ?: "desc") as String
            val page = (query["page"] ?: 1) as Int
            val sort = (query["sort"] ?: "stars") as String

            client.getRepositories(
                    language = language,
                    order = order,
                    page = page,
                    sort = sort
                )
                .map { it.items }
                .toObservable()
                .flatMapIterable { it }
                .flatMapSingle { repos ->
                    val repository = repos.copy(language = query["language"] as String)

                    Single.zip<RepositoryEntity, UserEntity, Repository>(
                        Single.just(repository),
                        getUserEntity(repository),
                        BiFunction { r, u -> r.asBean(u.copy(key = repos.owner.key)) }
                    )
                }
                .toList()
                .toObservable()
        })

        return Observable.combineLatest(queries) { q ->
            q
                .map { it as? List<*> ?: throw IllegalArgumentException() }
                .map { repositories ->
                    repositories.map { it as Repository }.toMutableList()
                }
                .reduce { acc, list ->
                    acc.addAll(list)
                    acc
                }
        }.map {
            it.toList()
        }
    }

    private fun getUserEntity(repository: RepositoryEntity): Single<UserEntity> =
        if (repository.owner.name.isNullOrBlank()) {
            client.getUser(repository.owner.key).map { it.copy(key = repository.owner.key) }
        } else {
            Single.just(repository.owner.asUser())
        }

    private fun copyUserIntoRepository(repository: RepositoryEntity): Single<Repository> =
        getUserEntity(repository)
            .map {
                repository.asBean(it)
            }

    companion object {

        const val PAGE_SIZE = 30

    }

}
