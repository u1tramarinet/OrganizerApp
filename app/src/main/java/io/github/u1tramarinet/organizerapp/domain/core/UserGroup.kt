package io.github.u1tramarinet.organizerapp.domain.core

data class UserGroup(val id: Int, val name: String, val users: List<User>)