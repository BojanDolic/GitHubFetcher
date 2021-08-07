package com.electrocoder.githubfetcher.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.ObservableBoolean
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.electrocoder.githubfetcher.databinding.AdapterLoadingErrorStatesBinding

class PagingLoadStatesAdapter(
    val retry: () -> Unit
) : LoadStateAdapter<PagingLoadStatesAdapter.ViewHolder>() {

    val loadingBool = ObservableBoolean(false)
    val errorBool = ObservableBoolean(false)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = AdapterLoadingErrorStatesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.loading = loadingBool
        binding.error = errorBool

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class ViewHolder(val binding: AdapterLoadingErrorStatesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            loadingBool.set(loadState is LoadState.Loading)
            errorBool.set(loadState !is LoadState.Loading)
        }

        init {
            binding.retryLoadingBtn.setOnClickListener {
                retry.invoke()
            }
        }

    }


}