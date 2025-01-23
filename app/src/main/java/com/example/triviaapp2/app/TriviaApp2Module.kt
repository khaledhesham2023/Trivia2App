package com.example.triviaapp2.app

import android.app.Application
import com.example.triviaapp2.data.network.ServicesApi
import com.example.triviaapp2.data.repository.QuestionsRepoImpl
import com.example.triviaapp2.domain.repository.QuestionsRepo
import com.example.triviaapp2.utils.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TriviaApp2Module : Application() {

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): ServicesApi = retrofit.create(ServicesApi::class.java)

    @Provides
    @Singleton
    fun providesRetrofit(url: String, gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesBaseUrl(): String = Urls.BASE_URL

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor {
        val request = it.request()
        it.proceed(request)
    }
        .connectTimeout(15L, TimeUnit.SECONDS)
        .readTimeout(15L, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun providesQuestionsRepo(api: ServicesApi): QuestionsRepo = QuestionsRepoImpl(api)
}