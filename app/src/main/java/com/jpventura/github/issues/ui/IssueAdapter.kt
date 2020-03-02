package com.jpventura.github.issues.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jpventura.domain.bean.Issue
import com.jpventura.github.R
import com.jpventura.github.core.ktx.inflate
import com.jpventura.github.core.ui.RecyclerViewArrayAdapter
import java.text.DateFormat
import kotlinx.android.synthetic.main.item_issue.view.*

class IssueAdapter : RecyclerViewArrayAdapter<Issue, IssueAdapter.ViewHolder>() {

    private var onItemClickListener: ((
        parent: ViewGroup,
        view: View?,
        position: Int,
        id: Long
    ) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        val issue = getItem(position)
        return "${issue.parentKey}/${issue.key}".hashCode().toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issue = getItem(position)

        if (holder.itemView.tag == null) {
            holder.itemView.tag = issue
        }

        Glide
            .with(holder.avatar)
            .load(issue.user.avatarUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_profile)
            .circleCrop()
            .into(holder.avatar)

        holder.body.text = issue.body
        holder.createdAt.text = DateFormat.getDateInstance().format(issue.createdAt)
        holder.username.text = if (issue.user.name.isBlank()) issue.user.key else issue.user.name
        holder.title.text = issue.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = parent.inflate(R.layout.item_issue)

        item.setOnClickListener {
            (item.tag as? Issue)?.let {
                val position = getPosition(it)
                val id = getItemId(position)
                onItemClickListener?.invoke(parent, item, position, id)
            }
        }

        return ViewHolder(item)
    }

    fun setOnItemClickListener(
        onItemClickListener: ((parent: ViewGroup, item: View?, position: Int, id: Long) -> Unit)?
    ) {
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val avatar: ImageView = item.image_issue_avatar
        val body: TextView = item.label_issue_body
        val createdAt: TextView = item.label_issue_createdat
        val title: TextView = item.label_issue_title
        val username: TextView = item.label_issue_username
    }
}
