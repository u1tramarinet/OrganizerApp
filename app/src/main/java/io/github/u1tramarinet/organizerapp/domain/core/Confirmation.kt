package io.github.u1tramarinet.organizerapp.domain.core

sealed class Confirmation(
    open val attendance: Boolean,
    open val fee: Boolean
) {
    data class Registered(
        override val attendance: Boolean,
        override val fee: Boolean
    ) : Confirmation(attendance = attendance, fee = fee) {
        init {
            require(isValid())
        }
    }

    class Draft(
        override val attendance: Boolean,
        override val fee: Boolean
    ) : Confirmation(attendance = attendance, fee = fee) {
        companion object {
            fun initialValue() = Draft(attendance = true, fee = true)
        }
    }

    fun isValid(): Boolean = attendance || fee
}