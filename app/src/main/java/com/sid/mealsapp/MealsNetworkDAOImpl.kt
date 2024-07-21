package com.sid.mealsapp

import com.brt.common.entity.AppError
import com.brt.common.entity.AppSuccess
import com.brt.common.entity.ResponseHandler
import com.sid.mealsapp.utils.ErrorCodeType
import javax.inject.Inject

class MealsNetworkDAOImpl @Inject constructor(val mealsApiService: MealsApiService) : MealsNetworkDAO {
    override suspend fun getMeals(): ResponseHandler<MealEntity> {
        return try {
            val response = mealsApiService.getMeals()
            if (response.isSuccessful) {
                val apiResponseEntity = response.body()
                apiResponseEntity?.let {
                    it.meals?.let { mealEntities -> AppSuccess(mealEntities.toMutableList()) }
                } ?: run {
                    AppError("", ErrorCodeType.SERVER_ERROR)
                }
            } else {
                AppError("", ErrorCodeType.SERVER_ERROR)
            }
        } catch (e: Exception) {
            AppError("", ErrorCodeType.SERVER_ERROR)

        }
    }
}