package com.u1tramarinet.organizerapp.data.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    suspend fun register(item: T): Boolean

    fun getStream(id: Int): Flow<T>

    fun getAllStream(): Flow<List<T>>

    suspend fun update(item: T): Boolean

    suspend fun remove(id: Int): Boolean

    suspend fun clear(): Boolean
}