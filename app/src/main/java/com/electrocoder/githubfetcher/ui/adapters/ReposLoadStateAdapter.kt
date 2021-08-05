package com.electrocoder.githubfetcher.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.electrocoder.githubfetcher.databinding.AdapterLoadingErrorStatesBinding

class ReposLoadStateAdapter(
    val retry: () -> Unit
) : LoadStateAdapter<ReposLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = AdapterLoadingErrorStatesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class ViewHolder(val binding: AdapterLoadingErrorStatesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBarLoading.isVisible = loadState is LoadState.Loading
            binding.retryLoadingBtn.isVisible = loadState !is LoadState.Loading
        }

        init {
            binding.retryLoadingBtn.setOnClickListener {
                retry.invoke()
            }
        }

    }


}