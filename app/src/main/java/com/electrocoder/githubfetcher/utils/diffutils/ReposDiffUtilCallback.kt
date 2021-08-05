package com.electrocoder.githubfetcher.utils.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.electrocoder.githubfetcher.models.Repo

class ReposDiffUtilCallback : DiffUtil.ItemCallback<Repo>() {

    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.commitsUrl == newItem.commitsUrl
                && oldItem.repoName == newItem.repoName
                && oldItem.description == newItem.description
                && oldItem.watchers == newItem.watchers
    }
}