package com.electrocoder.githubfetcher.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.electrocoder.githubfetcher.databinding.RepoListItemBinding
import com.electrocoder.githubfetcher.models.Repo
import com.electrocoder.githubfetcher.utils.diffutils.ReposDiffUtilCallback

class ReposPagingAdapter : PagingDataAdapter<Repo, ReposPagingAdapter.ViewHolder>(ReposDiffUtilCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RepoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: RepoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo?) {
            binding.repo = repo
        }



    }

}