package com.electrocoder.githubfetcher.di.modules

import com.electrocoder.githubfetcher.api.GitHubApi
import com.electrocoder.githubfetcher.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(api: GitHubApi): Repository {
        return Repository(api)
    }
}