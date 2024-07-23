package com.sid.mealsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sid.mealsapp.MealEntity
import com.sid.mealsapp.databinding.LayoutItemsMealsBinding
import com.sid.mealsapp.utils.AppUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MealsAdapter @Inject constructor() :
    RecyclerView.Adapter<MealsAdapter.AdapterViewHolder>() {
    var onClickSharedFlow = MutableSharedFlow<MealEntity>()
    var mealsList = mutableListOf<MealEntity>()

//    @Inject


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding =
            LayoutItemsMealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(binding)
    }

    fun setData(studentList: List<MealEntity>) {
        this.mealsList.clear()
        this.mealsList.addAll(studentList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val studentEntity = mealsList[position]
        holder.setData(studentEntity)
    }

    inner class AdapterViewHolder(val layoutStudentBinding: LayoutItemsMealsBinding) :
        ViewHolder(layoutStudentBinding.root) {
        init {


            layoutStudentBinding.llImage.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    onClickSharedFlow.emit(mealsList[adapterPosition])
                }
            }
        }

        fun setData(mealEntity: MealEntity) {
            try {
                var incredientsAdapter = IncredientsAdapter()

                layoutStudentBinding.rvIncredients.layoutManager = LinearLayoutManager(
                    layoutStudentBinding.rvIncredients.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                layoutStudentBinding.rvIncredients.adapter = incredientsAdapter

                mealEntity.strMeal.let {
                    layoutStudentBinding.tvMealName.text = it
                }

                mealEntity.strMealThumb.let {
                    if (it != null) {
                        AppUtils.loadImageWithURL(
                            layoutStudentBinding.ivMeal.context,
                            layoutStudentBinding.ivMeal,
                            it
                        )
                    }
                }
                var list = getIngredients(mealEntity)

                incredientsAdapter.setData(list)


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

   /* fun getIngredients(meal: MealDetailEntity): List<String> {
        return meal::class.memberProperties
            .filter { it.name.startsWith("strIngredient") }
            .mapNotNull { it.get(meal as Nothing) as? String }
    }*/


    fun getIngredients(meal: MealEntity): List<String> {
        return listOfNotNull(
            meal.strIngredient1,
            meal.strIngredient2,
            meal.strIngredient3,
            meal.strIngredient4,
            meal.strIngredient5,
            meal.strIngredient6,
            meal.strIngredient7,
            meal.strIngredient8,
            meal.strIngredient9,
            meal.strIngredient10,
            meal.strIngredient11,
            meal.strIngredient12,
            meal.strIngredient13,
            meal.strIngredient14,
            meal.strIngredient15,
            meal.strIngredient16,
            meal.strIngredient17,
            meal.strIngredient18,
            meal.strIngredient19,
            meal.strIngredient20
        ).filter { it.isNotBlank() }
    }

    /*fun filterList(filterdNames: ArrayList<StudentEntity>) {
        setData(filterdNames)
        notifyDataSetChanged()
    }*/
}