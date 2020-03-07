package com.luizalabs.github.repositories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.luizalabs.github.R
import com.luizalabs.github.ui.main.RepositoriesViewModel

class RepositoriesFragment : Fragment() {

    companion object {
        fun newInstance(arguments: Bundle? = Bundle()): Fragment {
            val fragment = RepositoriesFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    private lateinit var viewModel: RepositoriesViewModel

    lateinit var state: State

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("ventura", savedInstanceState?.toString() ?: "nada")

        state = State.Uninitialized

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)
    }

}
