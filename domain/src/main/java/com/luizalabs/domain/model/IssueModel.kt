package com.luizalabs.domain.model

import com.luizalabs.core.domain.model.PairPersistedModel
import com.luizalabs.domain.bean.Issue
import kotlin.collections.Map.Entry
import io.reactivex.Observable
import io.reactivex.Single

interface IssueModel : PairPersistedModel<String, Long, Issue> {

    override fun clear(): Single<Int>

    override fun createOne(value: Issue): Single<String>

    override fun create(values: Collection<Issue>): Single<List<Issue>>

    override fun destroy(values: Collection<String>): Single<Int>

    override fun destroyOne(value: Issue): Single<Int>

    override fun containsKey(key: Pair<String, Long>): Single<Boolean>

    override fun containsValue(value: Issue): Single<Boolean>

    override fun entries(): Observable<List<Entry<Pair<String, Long>, Issue>>>

    override fun find(): Observable<List<Issue>>

    override fun findOne(id: Pair<String, Long>): Observable<Issue>

    override fun keys(): Observable<List<Pair<String, Long>>>

    override fun size(): Observable<Int>

    override fun update(values: Collection<Issue>): Single<List<Issue>>

    override fun updateOne(value: Issue): Single<String>

}
