package io.github.u1tramarinet.organizerapp.domain.dutch.entiry

class Dutch private constructor(
    val id: Int,
    val title: String? = null,
    val totalPrice: Int = 0,
    val members: List<DutchMember> = emptyList(),
)