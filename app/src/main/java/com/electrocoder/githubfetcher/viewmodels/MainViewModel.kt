package com.electrocoder.githubfetcher.viewmodels

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.electrocoder.githubfetcher.di.AppComponent
import com.electrocoder.githubfetcher.models.ApiResponse
import com.electrocoder.githubfetcher.models.User
import com.electrocoder.githubfetcher.models.UsersResponse
import com.electrocoder.githubfetcher.repository.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import kotlin.math.log


class MainViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private var searchJob: Job = Job()

    private val searchText: MutableLiveData<String> = MutableLiveData()

    val usersList: LiveData<PagingData<User>> = Transformations.switchMap(searchText) { searchQuery ->
        repository.searchUsers(searchQuery)
            .asLiveData(viewModelScope.coroutineContext)
            .cachedIn(viewModelScope)
    }

    fun setSearchQuery(q: String) {
        var textChange = ""

            if(searchJob.isActive)
                searchJob.cancel()

            searchJob = viewModelScope.launch {
                delay(1200)
                if(q.isNotEmpty() && q.length > 3 && q != textChange) {
                    searchText.value = q
                    textChange = q
                }
        }

    }

}