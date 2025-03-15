package io.github.u1tramarinet.organizerapp.domain.core

data class User(
    val id: Int,
    val name: String,
    val phoneNumber: String?,
    val mailAddress: String?,
)