package com.nt.ecommerce.di

import com.nt.ecommerce.framework.network.SearchService
import com.nt.ecommerce.framework.search.SearchRepository
import com.nt.ecommerce.framework.search.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object SearchModule {

    @ActivityScoped
    @Provides
    fun provideSearchUseCase(searchService: SearchService): SearchUseCase = SearchRepository(searchService)
}