package com.electrocoder.githubfetcher.di

import com.electrocoder.githubfetcher.MainActivity
import com.electrocoder.githubfetcher.ui.fragments.MainFragment
import com.electrocoder.githubfetcher.ui.fragments.RepositoriesFragment
import com.electrocoder.githubfetcher.ui.fragments.UserDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Module
abstract class FragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesMainFragment(): MainFragment


    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesUserFragment(): UserDetailsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesReposFragment(): RepositoriesFragment


    @MustBeDocumented
    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class FragmentScoped

}