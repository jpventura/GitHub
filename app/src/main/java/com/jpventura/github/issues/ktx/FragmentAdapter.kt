package com.jpventura.github.issues.ktx

import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.bean.Repository
import com.jpventura.github.R
import com.jpventura.github.core.ktx.visible
import com.jpventura.github.core.ui.Failure
import com.jpventura.github.core.ui.Loading
import com.jpventura.github.core.ui.State
import com.jpventura.github.core.ui.Success
import com.jpventura.github.core.ui.Uninitialized
import com.jpventura.github.issues.ui.IssueAdapter
import com.jpventura.github.repositories.ui.RepositoryAdapter

@BindingAdapter("state")
fun setRecyclerView(view: RecyclerView, state: State) {
    if (state !is Success) return

    when (val adapter = view.adapter) {
        is IssueAdapter -> {
            adapter.addAll(state.result.map { it as Issue }.toList())
        }
        is RepositoryAdapter -> {
            adapter.addAll(state.result.map { it as Repository }.toList())
        }
    }
}

@BindingAdapter("state")
fun setProgressBar(view: ProgressBar, state: State) {
    when (state) {
        Loading -> {
            view.visible = true
        }
        else -> {
            view.visible = false
        }
    }
}

@BindingAdapter("state")
fun setTextView(view: TextView, state: State) {
    when (state) {
        is Uninitialized -> {
            view.visible = true
        }
        is Loading -> {
            view.visible = false
        }
        is Success -> {
            if (state.result.isEmpty()) {
                view.visible = true
                view.text = view.context.getString(R.string.label_issues_empty)
            } else {
                view.visible = false
            }
        }
        is Failure -> {
            view.text = view.context.getString(R.string.label_issues_error)
            view.visible = true
        }
    }
}
