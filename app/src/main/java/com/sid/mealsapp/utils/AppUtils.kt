package com.sid.mealsapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.transition.Transition
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.sid.mealsapp.R

class AppUtils {
    companion object{

        val baseUrl = "https://www.themealdb.com/"

        fun loadImageWithURL(
            context: Context,
            iv: ImageView,
            url: String,
            placeholder: Drawable? = null
        ) {
            try {
                Glide.with(context)
                    .load(url)
                    .placeholder(placeholder)
                    .error(R.mipmap.ic_launcher_round)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(object : CustomTarget<Drawable>() {
                        override fun onLoadCleared(placeholder: Drawable?) {
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            iv.setImageDrawable(resource)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            iv.setImageDrawable(errorDrawable)
                        }

                    })
            } catch (e: Exception) {
                logException(e)
            }
        }

        fun logException(
            exception: java.lang.Exception,
            tag: String = "LOG_ERROR----",
            msg: String = ""
        ) {
            Log.e(tag, msg, exception)
        }
    }

}