package io.github.u1tramarinet.organizerapp.ui

sealed class ScreenState<out T> {
    data object Loading : ScreenState<Nothing>()
    data class Success<out T>(val data: T) : ScreenState<T>()
    data class Error(val errorCode: Int) : ScreenState<Nothing>()
}