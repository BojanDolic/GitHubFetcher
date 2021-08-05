package com.electrocoder.githubfetcher.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.electrocoder.githubfetcher.models.Repo
import com.electrocoder.githubfetcher.repository.Repository
import javax.inject.Inject

class RepositoriesViewModel @Inject  constructor(
    val repository: Repository
) : ViewModel() {

    fun getRepositories(url: String): LiveData<PagingData<Repo>> {
        return repository.getUserRepositories(url).asLiveData()
    }


}