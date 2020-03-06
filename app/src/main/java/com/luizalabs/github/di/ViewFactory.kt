package com.luizalabs.github.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.luizalabs.github.repositories.RepositoriesFragment

class ViewFactory() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return if (className == RepositoriesFragment::class.java.name) {
            RepositoriesFragment()
        } else {
            super.instantiate(classLoader, className)
        }
    }

}
