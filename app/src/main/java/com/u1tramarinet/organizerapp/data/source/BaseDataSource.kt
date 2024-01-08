package com.u1tramarinet.organizerapp.data.source

import kotlinx.coroutines.flow.Flow

interface BaseDataSource<T> {
    fun getAllStream(): Flow<List<T>>
    fun getStream(id: Int): Flow<T>
    suspend fun add(item: T): Boolean
    suspend fun update(item: T): Boolean
    suspend fun remove(id: Int): Boolean
    suspend fun clear(): Boolean
}