package com.electrocoder.githubfetcher.di

import android.app.Application
import com.electrocoder.githubfetcher.DaggerApplication
import com.electrocoder.githubfetcher.di.modules.NetworkModule
import com.electrocoder.githubfetcher.di.modules.RepositoryModule
import com.electrocoder.githubfetcher.di.modules.ViewModelModule
import com.electrocoder.githubfetcher.repository.Repository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }



}