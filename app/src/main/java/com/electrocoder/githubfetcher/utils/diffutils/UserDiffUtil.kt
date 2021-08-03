package com.electrocoder.githubfetcher.utils.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.electrocoder.githubfetcher.models.User

class UserDiffUtil : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.company == newItem.company && oldItem.avatarUrl == newItem.avatarUrl &&
                oldItem.followers == newItem.followers
    }

}