package com.sid.mealsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sid.mealsapp.databinding.LayoutItemIncredentsBinding
import com.sid.mealsapp.utils.AppUtils
import javax.inject.Inject

class IncredientsAdapter @Inject constructor() :
    RecyclerView.Adapter<IncredientsAdapter.AdapterViewHolder>() {
    var incredientsList = mutableListOf<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding =
            LayoutItemIncredentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(binding)
    }

    fun setData(incredientsList: List<String>) {
        this.incredientsList.clear()
        this.incredientsList.addAll(incredientsList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return incredientsList.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val incredient = incredientsList[position]
        holder.setData(incredient)
    }

    inner class AdapterViewHolder(val layoutStudentBinding: LayoutItemIncredentsBinding) :
        ViewHolder(layoutStudentBinding.root) {


        fun setData(incredient: String) {
            try {
                incredient.let {
                    layoutStudentBinding.tvIncredientName.text = it

                    val incredentimageUrl = "https://www.themealdb.com/images/ingredients/$it.png"
                    AppUtils.loadImageWithURL(
                        layoutStudentBinding.ivIncredient.context,
                        layoutStudentBinding.ivIncredient,
                        incredentimageUrl
                    )
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    /*fun filterList(filterdNames: ArrayList<StudentEntity>) {
        setData(filterdNames)
        notifyDataSetChanged()
    }*/
}