package com.electrocoder.githubfetcher.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.electrocoder.githubfetcher.di.viewmodelfactory.ViewModelFactory
import com.electrocoder.githubfetcher.viewmodels.CommitsViewModel
import com.electrocoder.githubfetcher.viewmodels.MainViewModel
import com.electrocoder.githubfetcher.viewmodels.RepositoriesViewModel
import com.electrocoder.githubfetcher.viewmodels.UserDetailsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun userDetailsViewModel(userDetailsViewModel: UserDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun reposViewModel(repositoriesViewModel: RepositoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommitsViewModel::class)
    abstract fun commitsViewModel(commitsViewModel: CommitsViewModel): ViewModel



    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

}