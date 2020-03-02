package com.jpventura.github.repositories.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jpventura.domain.bean.Repository
import com.jpventura.github.R
import com.jpventura.github.core.ktx.inflate
import com.jpventura.github.core.ui.RecyclerViewArrayAdapter
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter : RecyclerViewArrayAdapter<Repository, RepositoryAdapter.ViewHolder>() {

    private var onItemClickListener: ((
        parent: ViewGroup,
        view: View?,
        position: Int,
        id: Long
    ) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = getItem(position).key.hashCode().toLong()

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = getItem(position)

        holder.itemView.tag = repository

        Glide
            .with(holder.avatar)
            .load(repository.user.avatarUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_profile)
            .circleCrop()
            .into(holder.avatar)

        holder.description.text = repository.description
        holder.forks.text = repository.forks.toString()
        holder.login.text = repository.owner.key
        holder.username.text = repository.owner.name ?: repository.user.name
        holder.stars.text = repository.stargazersCount.toString()
        holder.title.text = repository.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = parent.inflate(R.layout.item_repository)

        item.setOnClickListener {
            (item.tag as? Repository)?.let {
                val position = getPosition(it)
                onItemClickListener?.invoke(parent, item, position, getItemId(position))
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
        val avatar: ImageView = item.image_repository_avatar
        val description: TextView = item.label_repository_body
        val forks: TextView = item.label_repositories_fork
        val login: TextView = item.label_repository_login
        val stars: TextView = item.label_repository_star
        val title: TextView = item.label_repository_title
        val username: TextView = item.label_repository_ownername
    }
}
