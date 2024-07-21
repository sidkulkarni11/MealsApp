package com.sid.mealsapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.sid.mealsapp.MealsApiService
import com.sid.mealsapp.MealsNetworkDAO
import com.sid.mealsapp.MealsNetworkDAOImpl
import com.sid.mealsapp.utils.AppUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/*@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppUtils.baseUrl) // Update with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMealsApiService(): MealsApiService? {
        return Retrofit.Builder()
            .baseUrl(AppUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMealsNetworkDAO(mealsApiService: MealsApiService): MealsNetworkDAO {
        return MealsNetworkDAOImpl(mealsApiService)
    }
}*/

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context)) // Add the Chucker interceptor
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) // Use the OkHttpClient with Chucker interceptor
            .build()
    }

    @Provides
    @Singleton
    fun provideMealsApiService(retrofit: Retrofit): MealsApiService {
        return retrofit.create(MealsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMealsNetworkDAO(mealsApiService: MealsApiService): MealsNetworkDAO {
        return MealsNetworkDAOImpl(mealsApiService)
    }
}
