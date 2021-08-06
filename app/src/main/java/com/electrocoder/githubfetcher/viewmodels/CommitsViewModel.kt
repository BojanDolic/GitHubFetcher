package com.electrocoder.githubfetcher.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.electrocoder.githubfetcher.models.Repo
import com.electrocoder.githubfetcher.models.commit.Commit
import com.electrocoder.githubfetcher.repository.Repository
import javax.inject.Inject

class CommitsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val urlString: MutableLiveData<String> = MutableLiveData()

    val commits: LiveData<PagingData<Commit>> = Transformations.switchMap(urlString) { url ->
        repository.getRepoCommits(url)
            .cachedIn(viewModelScope)
            .asLiveData(viewModelScope.coroutineContext)
    }

    fun getCommits(url: String) {
        urlString.value = url
    }

}