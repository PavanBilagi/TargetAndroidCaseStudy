package com.target.targetcasestudy.ui.base

sealed class Status<T>(open val data: T?) {
    class Success<T>(override val data: T) : Status<T>(data)
    class Loading<T>(override val data: T? = null) : Status<T>(data)
    class Error<T>(override val data: T? = null, val throwable: Throwable?) : Status<T>(data)
}
