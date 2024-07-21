package com.sid.mealsapp

import com.brt.common.entity.AppError
import com.brt.common.entity.AppSuccess
import com.brt.common.entity.ResponseHandler
import com.sid.mealsapp.utils.AppUtils
import com.sid.mealsapp.utils.ErrorCodeType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MealsRepository @Inject constructor(
    private val mealsNetworkDAO: MealsNetworkDAO
) {
    fun getMeals(): Flow<ResponseHandler<MealEntity>> = flow {
        try {
            val result = mealsNetworkDAO.getMeals()
            when (result) {
                is AppSuccess -> {
                    try {
                        val mealsList = result.data
                        emit(AppSuccess(mealsList))
                    } catch (e: Exception) {
                        AppUtils.logException(e)
                    }
                }
                is AppError -> {
                    emit(AppError(result.message, ErrorCodeType.SERVER_ERROR))
                }
                else -> {
                }
            }
            emit(result)
        } catch (e: Exception) {
            emit(AppError("", ErrorCodeType.INTERNAL_ERROR))
        }
    }.flowOn(Dispatchers.IO)
}

