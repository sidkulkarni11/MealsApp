package com.brt.common.entity

import com.sid.mealsapp.utils.ErrorCodeType

sealed class ResponseHandler<T : Any> {


}

data class AppLoading<T : Any>(val data: T?) : ResponseHandler<T>()
data class AppSuccess<T : Any>(val data: MutableList<T>, val message: String = "") : ResponseHandler<T>()
data class AppError<T : Any>(val message: String = "", val errorCodeType: ErrorCodeType) : ResponseHandler<T>()
