package com.sid.mealsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brt.common.entity.AppError
import com.brt.common.entity.AppLoading
import com.brt.common.entity.ResponseHandler
import com.sid.mealsapp.utils.ErrorCodeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val repository: MealsRepository
) : ViewModel() {

    private val mealMutableLiveData = MutableLiveData<ResponseHandler<MealEntity>>()
    val mealLiveData: LiveData<ResponseHandler<MealEntity>> = mealMutableLiveData

    init {
        fetchMeals()
    }

    fun fetchMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMeals()
                .onStart {
                    mealMutableLiveData.postValue(AppLoading(null))
                }
                .catch {
                    mealMutableLiveData.postValue(AppError("Error message", ErrorCodeType.DATABASE_ERROR))
                }
                .collect {
                    mealMutableLiveData.postValue(it)
                }
        }
    }
}

