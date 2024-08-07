package com.sid.mealsapp

import com.brt.common.entity.APIResponseEntity
import retrofit2.Response
import retrofit2.http.GET

interface MealsApiService {
//    @GET("api/json/v1/1/random.php")
    @GET("api/json/v2/1/randomselection.php")
    suspend fun getMeals(): Response<APIResponseEntity<MealEntity>>
}
