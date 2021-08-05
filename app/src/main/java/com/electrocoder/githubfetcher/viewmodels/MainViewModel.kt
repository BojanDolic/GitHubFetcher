package com.electrocoder.githubfetcher.viewmodels

import android.util.Log
import androidx.lifecycle.*
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

    //@Inject lateinit

    private var searchJob: Job = Job()

    private val searchText: MutableLiveData<String> = MutableLiveData()

    private val users: MutableLiveData<UsersResponse> = MutableLiveData()

    val usersList: LiveData<UsersResponse> = Transformations.switchMap(searchText) { searchQuery ->
        repository.searchUsers(searchQuery).asLiveData(viewModelScope.coroutineContext)
    }


    /*fun searchUsers(q: String): MutableLiveData<UsersResponse> {
        val result: MutableLiveData<UsersResponse> = MutableLiveData()
        viewModelScope.launch {
            repository.searchUsers(q)
                .collect { 
                    value: UsersResponse ->
                    users.value = value
                }
        }
        return result
    }*/


    fun setSearchQuery(q: String) {
        var textChange = ""

            if(searchJob.isActive)
                searchJob.cancel()

            searchJob = viewModelScope.launch {
                delay(1500)
                if(q.isNotEmpty() && q.length > 3 && q != textChange) {
                    searchText.value = q
                    textChange = q
                }
        }

    }

}