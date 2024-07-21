package com.sid.mealsapp

import com.brt.common.entity.ResponseHandler

interface MealsNetworkDAO {
    suspend fun getMeals(): ResponseHandler<MealEntity>
}