package com.luizalabs.data.cache

import com.luizalabs.data.PersistedModelTest
import com.luizalabs.domain.bean.Issue

val samples = listOf(Issue(id = 1, path = "asdf"), Issue(id = 2, path = "asdf")).map { it.id to it }.toMap()

class MemoryModelTest: PersistedModelTest<Int, Issue>(samples = samples) {

    override val model = MemoryModel<Int, Issue>()

}
