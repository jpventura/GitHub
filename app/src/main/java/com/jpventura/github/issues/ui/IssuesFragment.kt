package com.jpventura.github.issues.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpventura.domain.bean.Repository
import com.jpventura.github.R
import com.jpventura.github.core.kodein.viewModel
import com.jpventura.github.core.ui.InjectedFragment
import com.jpventura.github.core.ui.OnEndlessScrollListener
import com.jpventura.github.core.ui.setDivider
import com.jpventura.github.databinding.FragmentIssueBinding
import com.jpventura.github.issues.IssuesContract
import com.jpventura.github.issues.vm.IssuesViewModel
import com.jpventura.github.main.ktx.activity
import kotlinx.android.synthetic.main.fragment_issue.*

class IssuesFragment : InjectedFragment(), IssuesContract.View {

    val vm: IssuesViewModel by viewModel()

    private lateinit var adapter: IssueAdapter

    private lateinit var binding: FragmentIssueBinding

    override lateinit var router: IssuesContract.Router

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIssueBinding.inflate(layoutInflater, container, false)
        binding.vm = vm
        router = activity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRecyclerViewCreated()
    }

    private fun onRecyclerViewCreated() {
        adapter = IssueAdapter().apply { setOnItemClickListener { _, _, position, _ ->
            router.goToBrowser(getItem(position))
        } }

        recycler_view.adapter = adapter
        recycler_view.setDivider(R.drawable.recyclerview_divider)

        recycler_view.addOnScrollListener(object : OnEndlessScrollListener(
            layoutManager = recycler_view.layoutManager as LinearLayoutManager
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                // TODO
            }
        })

        arguments?.getString(KEY_REPOSITORY_KEY)?.let {
            vm.getIssuesAsync(it)
        }
    }

    companion object {

        const val TAG = "IssuesFragment"

        const val KEY_REPOSITORY_KEY = "repository_key"
        const val KEY_REPOSITORY_NAME = "repository_name"
        const val KEY_USER_NAME = "user_name"

        fun newInstance(repositoryId: String): IssuesFragment =

            IssuesFragment().apply {
                arguments = Bundle().apply { putString(KEY_REPOSITORY_KEY, repositoryId) }
            }

        fun newInstance(repository: Repository): IssuesFragment =
            newInstance(repositoryId = repository.key)
    }
}
