package com.u1tramarinet.organizerapp.domain.core

sealed class Venue(
    open val name: String,
) {
    data class Registered(
        val id: Int = 0,
        override val name: String,
        val postCode: String? = null,
        val address: String? = null,
        val url: String? = null,
    ) : Venue(name = name)

    data class Summary(
        val id: Int = 0,
        override val name: String,
    ) : Venue(name = name) {
        companion object {
            fun from(registered: Registered): Summary {
                return Summary(
                    id = registered.id,
                    name = registered.name,
                )
            }
        }
    }

    data class Draft(
        override val name: String,
    ) : Venue(name = name)
}