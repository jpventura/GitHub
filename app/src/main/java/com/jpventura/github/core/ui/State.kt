package com.jpventura.github.core.ui

sealed class State

object Uninitialized : State()

object Loading : State()

object Reloading : State()

class Success(val result: List<*>) : State()

class Failure(throwable: Throwable) : State()
