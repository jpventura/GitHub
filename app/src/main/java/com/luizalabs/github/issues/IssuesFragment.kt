package com.luizalabs.github.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.luizalabs.github.R
import com.luizalabs.github.ui.main.RepositoriesViewModel

class IssuesFragment : Fragment() {

    companion object {
        fun newInstance() =
            IssuesFragment()
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

}
