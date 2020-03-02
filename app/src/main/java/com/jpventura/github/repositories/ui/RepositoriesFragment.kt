package com.jpventura.github.repositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpventura.domain.bean.Repository
import com.jpventura.github.R
import com.jpventura.github.core.kodein.viewModel
import com.jpventura.github.core.ktx.toObservable
import com.jpventura.github.core.ui.InjectedFragment
import com.jpventura.github.core.ui.OnEndlessScrollListener
import com.jpventura.github.core.ui.setDivider
import com.jpventura.github.databinding.FragmentRepositoriesBinding
import com.jpventura.github.main.ktx.activity
import com.jpventura.github.repositories.RepositoriesContract
import com.jpventura.github.repositories.vm.RepositoriesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_issue.*

class RepositoriesFragment : InjectedFragment(), RepositoriesContract.View {

    private val vm: RepositoriesViewModel by viewModel()
    private val disposables = CompositeDisposable()

    @Inject lateinit var adapter: RepositoryAdapter
    @Inject lateinit var binding: FragmentRepositoriesBinding
    @Inject lateinit var searchView: SearchView

    private var language: String = ""

    override fun goToIssues(repository: Repository) {
        activity().goToIssuesView(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoriesBinding.inflate(layoutInflater, container, false)
        binding.vm = vm

        activity().supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard, menu)
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setQuery(FIRST_QUERY, false)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        onPrepareSearchView(menu)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRecyclerViewCreated()
    }

    private fun onPrepareSearchView(menu: Menu) {
        searchView
            .toObservable()
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (language != it) {
                    adapter.clear()
                }
                language = it
            }
            .subscribe {
                vm.getRepositoriesAsync(language = it, min = PAGE_SIZE)
            }.let { disposables.add(it) }
    }

    private fun onRecyclerViewCreated() {
        adapter = RepositoryAdapter()
        adapter.setOnItemClickListener { _, _, position, _ ->
            activity().goToIssuesView(adapter.getItem(position))
        }

        recycler_view.adapter = adapter
        recycler_view.setDivider(R.drawable.recyclerview_divider)

        recycler_view.addOnScrollListener(object : OnEndlessScrollListener(recycler_view.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                vm.getRepositoriesAsync(language = language, min = totalItemsCount + PAGE_SIZE)
            }
        })
    }

    companion object {

        const val FIRST_QUERY = "Kotlin"

        const val TAG = "RepositoriesFragment"

        const val PAGE_SIZE = 30

        fun newInstance() = RepositoriesFragment()
    }
}
