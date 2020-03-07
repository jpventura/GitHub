package com.luizalabs.github.di

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.luizalabs.github.repositories.RepositoriesFragment
import com.luizalabs.github.repositories.State


class ViewFactory(private val state: State) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        if (className != RepositoriesFragment::class.java.name) {
            return super.instantiate(classLoader, className)
        }


        val fragment = RepositoriesFragment()

        when (state) {
            is State.Uninitialized -> {

            }
            is State.Loading -> {

            }
            is State.Success<*> -> {

            }
            else -> {

            }
        }

        return if (className == RepositoriesFragment::class.java.name) {

    }

}


fun java.io.Serializable.toBundle(): Bundle {
    val b = Bundle()
    b.
}