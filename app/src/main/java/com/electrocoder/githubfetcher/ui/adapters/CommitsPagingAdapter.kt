package com.electrocoder.githubfetcher.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.electrocoder.githubfetcher.databinding.CommitItemBinding
import com.electrocoder.githubfetcher.models.commit.Commit
import com.electrocoder.githubfetcher.utils.diffutils.CommitsDiffUtilCallback

class CommitsPagingAdapter
    : PagingDataAdapter<Commit, CommitsPagingAdapter.CommitViewHolder>(CommitsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val binding = CommitItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val commit = getItem(position)
        if(commit != null)
            holder.bind(commit)
    }


    inner class CommitViewHolder(val binding: CommitItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(commit: Commit) {
                binding.commit = commit
            }

    }


}