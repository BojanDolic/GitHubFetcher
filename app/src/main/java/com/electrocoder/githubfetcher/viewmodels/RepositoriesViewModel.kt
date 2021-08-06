package com.electrocoder.githubfetcher.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.electrocoder.githubfetcher.models.Repo
import com.electrocoder.githubfetcher.repository.Repository
import javax.inject.Inject

class RepositoriesViewModel @Inject  constructor(
    val repository: Repository
) : ViewModel() {

    private val urlString: MutableLiveData<String> = MutableLiveData()

    val repositories: LiveData<PagingData<Repo>> = Transformations.switchMap(urlString) { url ->
        repository.getUserRepositories(url)
            .cachedIn(viewModelScope)
            .asLiveData(viewModelScope.coroutineContext)
    }

    fun getRepositories(url: String) {
        urlString.value = url
    }


}