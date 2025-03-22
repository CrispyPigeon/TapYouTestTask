package com.ds.tapyoutesttask.core.utils

sealed class Operation<out T> {
    data class Ok<out T>(val data: T) : Operation<T>()
    data class Error(val exception: Throwable) : Operation<Nothing>()

    val isSuccess: Boolean get() = this is Ok
    val isFailure: Boolean get() = this is Error

    fun getOrNull(): T? = (this as? Ok)?.data
    fun exceptionOrNull(): Throwable? = (this as? Error)?.exception
}