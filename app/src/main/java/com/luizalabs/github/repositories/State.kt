package com.luizalabs.github.repositories

import android.os.Bundle
import androidx.annotation.StringDef
import com.luizalabs.domain.bean.Repository
import java.io.Serializable

sealed class State {

    class Success<E : Serializable>(val list: List<E> = emptyList()) : State() { }

    class Fail(throwable: Throwable) : State() {}

    object Loading: State() {}

    object Uninitialized : State() {}

    companion object {
        @Retention(AnnotationRetention.SOURCE)
        @StringDef(STATE_UNINITIALIZED, STATE_FAIL, STATE_SUCCESS)
        annotation class ServiceName

        const val STATE_UNINITIALIZED = "power"
        const val STATE_FAIL = "fail"
        const val STATE_LOADING = "loading"
        const val STATE_SUCCESS = "success"
    }

    fun foo(v: State) {

        val p = Bundle()

        when(v) {
            Uninitialized -> {
                p.putString("state", "Fail")
                p.putString("state", "Loading")
                p.putString("state", "Uninitialized")
            }
            Loading -> {
                p.putString("state", "Fail")
                p.putString("state", "Loading")
                p.putString("state", "Uninitialized")
            }
            is Success<*> -> {
                val x : Repository
                p.putString("state", "Fail")
                p.putlist
                p.putSerializable("state", v.list)
                p.putString("state", "Loading")
                p.putString("state", "Uninitialized")
            }
            is Fail -> {
                p.putString("state", "Fail")
                p.putString("state", "Loading")
                p.putString("state", "Uninitialized")
            }
        }

    }
}
