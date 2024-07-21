package com.brt.common.entity

import com.sid.mealsapp.MealEntity

data class APIResponseEntity<T>(
    val meals: List<MealEntity>?
)