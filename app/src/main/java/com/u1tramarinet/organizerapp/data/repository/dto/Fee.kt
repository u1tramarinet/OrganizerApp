package com.u1tramarinet.organizerapp.data.repository.dto

sealed class Fee(open val id: Int = 0, open val price: Int) {
    data class Named(
        override val id: Int = 0,
        val title: String,
        override val price: Int,
    ) : Fee(id = id, price = price)

    data class Unnamed(
        override val id: Int = 0,
        override val price: Int,
    ) : Fee(id = id, price = price)

    data object Free : Fee(id = 1, price = 0)
}