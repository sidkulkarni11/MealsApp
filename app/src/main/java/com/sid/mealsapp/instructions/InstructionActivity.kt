package com.sid.mealsapp.instructions

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sid.mealsapp.MealEntity
import com.sid.mealsapp.databinding.ActivityInstructionBinding

class InstructionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstructionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstructionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvInstructions.paintFlags = binding.tvInstructions.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        val mealDetail = intent.getParcelableExtra<MealEntity>("meal_detail")

        if(mealDetail != null){
            val instructions = mealDetail.strInstructions

            if(!instructions.isNullOrEmpty()) {
                binding.tvInstructionsDetails.text = instructions
            }
        }
    }
}