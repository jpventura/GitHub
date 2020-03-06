package com.luizalabs.github.repositories

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import com.luizalabs.github.di.ViewFactory
import org.junit.After
import org.junit.Before

import org.junit.Test

class RepositoriesFragmentTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldShowViewOnLoadingState() {
        val args = Bundle().apply {
            putInt("selectedListItem", 0)
        }
        launchFragmentInContainer<RepositoriesFragment>(
            fragmentArgs = args,
            factory = ViewFactory()
        )
    }

    @Test
    fun shouldShowViewOnDataState() {
        val args = Bundle().apply {
            putInt("selectedListItem", 0)
        }
        launchFragmentInContainer<RepositoriesFragment>(
            fragmentArgs = args,
            factory = ViewFactory()
        )
    }

    @Test
    fun shouldShowViewOnLoadedState() {
        val args = Bundle().apply {
            putInt("selectedListItem", 0)
        }
        launchFragmentInContainer<RepositoriesFragment>(
            fragmentArgs = args,
            factory = ViewFactory()
        )
    }

    @Test
    fun shouldShowViewOnErrorState() {
        val args = Bundle().apply {
            putInt("selectedListItem", 0)
        }
        launchFragmentInContainer<RepositoriesFragment>(
            fragmentArgs = args,
            factory = ViewFactory()
        )
    }

}
