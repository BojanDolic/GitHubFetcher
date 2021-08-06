package com.electrocoder.githubfetcher.utils.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.electrocoder.githubfetcher.models.commit.Commit

class CommitsDiffUtilCallback : DiffUtil.ItemCallback<Commit>() {
    override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
        return oldItem.commit.message == newItem.commit.message
                && oldItem.commit.author.name == newItem.commit.author.name
                && oldItem.commit.author.date == newItem.commit.author.date
                && oldItem.commit.comment_count == newItem.commit.comment_count
    }


}