package com.yoga.githubusertest.common.di

import androidx.viewbinding.BuildConfig
import com.yoga.githubusertest.common.constant.Config
import com.yoga.githubusertest.common.service.AppServicesInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseModule {
    @Provides
    fun provideBaseUrl()= Config.Constant.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL:String): AppServicesInterface {
        val logging = HttpLoggingInterceptor()
        val httpclient = OkHttpClient.Builder()
        httpclient.addInterceptor(logging)

        if (BuildConfig.DEBUG) {
            // development build
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            // production build
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpclient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppServicesInterface::class.java)
    }
}