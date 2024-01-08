package com.u1tramarinet.organizerapp.di

import android.content.Context
import androidx.room.Room
import com.u1tramarinet.organizerapp.data.repository.EventRepository
import com.u1tramarinet.organizerapp.data.repository.EventRepositoryImpl
import com.u1tramarinet.organizerapp.data.repository.UserRepository
import com.u1tramarinet.organizerapp.data.repository.UserRepositoryImpl
import com.u1tramarinet.organizerapp.data.repository.VenueRepository
import com.u1tramarinet.organizerapp.data.repository.VenueRepositoryImpl
import com.u1tramarinet.organizerapp.data.source.EventDataSource
import com.u1tramarinet.organizerapp.data.source.room.AppDatabase
import com.u1tramarinet.organizerapp.data.source.room.RoomEventDataSource
import com.u1tramarinet.organizerapp.data.source.room.dao.EventDao
import com.u1tramarinet.organizerapp.data.source.room.dao.FeeDao
import com.u1tramarinet.organizerapp.data.source.room.dao.ParticipantDao
import com.u1tramarinet.organizerapp.data.source.room.dao.UserDao
import com.u1tramarinet.organizerapp.data.source.room.dao.VenueDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Binds
    @Singleton
    abstract fun bindsUserRepository(impl: UserRepositoryImpl): UserRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModules {
    @Binds
    @Singleton
    abstract fun bindsRoomEventDataSource(impl: RoomEventDataSource): EventDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object RoomModules {
    @Provides
    @Singleton
    fun provideEventDao(appDatabase: AppDatabase): EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideParticipantDao(appDatabase: AppDatabase): ParticipantDao {
        return appDatabase.participantDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideFeeDao(appDatabase: AppDatabase): FeeDao {
        return appDatabase.feeDao()
    }

    @Provides
    @Singleton
    fun provideVenueDao(appDatabase: AppDatabase): VenueDao {
        return appDatabase.venueDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app").build()
    }
}