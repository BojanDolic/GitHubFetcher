package com.electrocoder.githubfetcher.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.electrocoder.githubfetcher.databinding.UserLayoutBinding
import com.electrocoder.githubfetcher.models.User
import com.electrocoder.githubfetcher.utils.diffutils.UserDiffUtil

class UserListAdapter: ListAdapter<User, UserListAdapter.ViewHolder>(UserDiffUtil()) {

    interface OnUserClicked {
        fun onUserClicked(user: User)
    }

    lateinit var click: OnUserClicked

    fun setUserClickListener(click: OnUserClicked) {
        this.click = click
    }

    inner class ViewHolder(val binding: UserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun update(user: User) {
            binding.user = user
            binding.root.setOnClickListener {
                click.onUserClicked(getItem(bindingAdapterPosition))
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.update(user)
    }

}