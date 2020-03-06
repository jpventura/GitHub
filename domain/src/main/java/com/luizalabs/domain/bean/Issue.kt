package com.luizalabs.domain.bean

import com.luizalabs.core.domain.bean.Bean

data class Issue(override val id: Int, val path: String) : Bean<Int>
