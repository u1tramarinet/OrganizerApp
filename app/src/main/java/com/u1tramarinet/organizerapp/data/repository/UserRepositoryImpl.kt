package com.u1tramarinet.organizerapp.data.repository

import com.u1tramarinet.organizerapp.data.repository.dto.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun register(item: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun getStream(id: Int): Flow<User> {
        TODO("Not yet implemented")
    }

    override fun getAllStream(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: User): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun remove(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun clear(): Boolean {
        TODO("Not yet implemented")
    }
}