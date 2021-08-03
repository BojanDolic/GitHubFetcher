package com.electrocoder.githubfetcher.viewmodels

import androidx.lifecycle.*
import com.electrocoder.githubfetcher.models.User
import com.electrocoder.githubfetcher.repository.Repository
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val username: MutableLiveData<String> = MutableLiveData()

    val _user: LiveData<User> = Transformations.switchMap(username) { username ->
        repository.getUserByName(username).asLiveData()
    }

    val user: User? get() = _user.value

    fun queryByUserName(name: String) {
        if(name.isNotEmpty())
            username.value = name
    }

}