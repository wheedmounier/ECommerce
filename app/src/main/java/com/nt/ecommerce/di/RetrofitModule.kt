package com.nt.ecommerce.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nt.ecommerce.BuildConfig
import com.nt.ecommerce.framework.network.MockInterceptor
import com.nt.ecommerce.framework.network.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson,client: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideRetrofitService(retrofit: Retrofit): SearchService = retrofit.create(SearchService::class.java)

    @Provides
    @Singleton
    fun provideMockInterceptor(): Interceptor = MockInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .build()


}