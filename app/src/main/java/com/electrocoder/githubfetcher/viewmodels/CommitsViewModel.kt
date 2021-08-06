package com.electrocoder.githubfetcher.viewmodels

import androidx.lifecycle.ViewModel
import com.electrocoder.githubfetcher.repository.Repository
import javax.inject.Inject

class CommitsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

}