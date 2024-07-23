package com.sid.mealsapp.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.sid.mealsapp.R
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

                    Glide.with(layoutStudentBinding.llIncredent.context)
                        .load(incredentimageUrl)
                        .error(R.mipmap.ic_launcher_round)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(object : CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {
                            }

                            override fun onResourceReady(
                                resource: Drawable,
                                transition: Transition<in Drawable>?
                            ) {
                                layoutStudentBinding.ivIncredient.setImageDrawable(resource)
                            }

                            override fun onLoadFailed(errorDrawable: Drawable?) {
                                super.onLoadFailed(errorDrawable)
                                layoutStudentBinding.ivIncredient.setImageDrawable(errorDrawable)
                            }

                        })
                  /*  AppUtils.loadImageWithURL(
                        layoutStudentBinding.ivIncredient.context,
                        layoutStudentBinding.ivIncredient,
                        incredentimageUrl
                    )*/
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