package com.example.triviaapp2.app

import android.app.Application
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TriviaApp2Module : Application() {

}