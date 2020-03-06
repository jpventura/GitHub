package com.luizalabs.github.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.luizalabs.github.R
import com.luizalabs.github.ui.main.RepositoriesViewModel

class RepositoriesFragment : Fragment() {

    companion object {
        fun newInstance() =
            RepositoriesFragment()
    }

    private lateinit var viewModel: RepositoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)
    }

    inner class Factory() : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return if (className == RepositoriesFragment::class.java.name) {
                RepositoriesFragment()
            } else {
                super.instantiate(classLoader, className)
            }
        }
    }

}
