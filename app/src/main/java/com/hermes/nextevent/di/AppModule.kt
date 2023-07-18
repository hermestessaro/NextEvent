package com.hermes.nextevent.di

import com.hermes.nextevent.data.remote.ApiService
import com.hermes.nextevent.data.remote.EventApi
import com.hermes.nextevent.data.remote.repository.EventRepositoryImpl
import com.hermes.nextevent.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEventApi(): EventApi {
        return ApiService.api
    }

    @Provides
    @Singleton
    fun provideEventRepository(api: EventApi): EventRepository {
        return EventRepositoryImpl(api)
    }

}