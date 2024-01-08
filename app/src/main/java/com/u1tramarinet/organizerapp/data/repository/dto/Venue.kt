package com.u1tramarinet.organizerapp.data.repository.dto

data class Venue(
    val id: Int = 0,
    val name: String,
    val postCode: String? = null,
    val address: String? = null,
    val url: String? = null,
)