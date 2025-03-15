package io.github.u1tramarinet.organizerapp.di

import io.github.u1tramarinet.organizerapp.data.repository.EventRepositoryImpl
import io.github.u1tramarinet.organizerapp.data.repository.VenueRepositoryImpl
import io.github.u1tramarinet.organizerapp.domain.repository.EventRepository
import io.github.u1tramarinet.organizerapp.domain.repository.VenueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {
    @Binds
    @Singleton
    abstract fun bindsEventRepository(impl: EventRepositoryImpl): EventRepository

    @Binds
    @Singleton
    abstract fun bindsVenueRepository(impl: VenueRepositoryImpl): VenueRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModules {
}