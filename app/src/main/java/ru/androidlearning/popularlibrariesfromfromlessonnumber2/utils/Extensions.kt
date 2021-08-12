package ru.androidlearning.popularlibrariesfromfromlessonnumber2.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import kotlin.math.roundToInt

private const val DEFAULT_IMAGE_SIZE = 50

@Suppress("IMPLICIT_CAST_TO_ANY")
fun TextView.setStartDrawableCircleImageFromUrl(url: String?, imageSize: Int? = null, placeholder: Int = 0) {
    val glideUrl = if (url.isNullOrBlank()) placeholder else GlideUrl(url)

    Glide.with(context)
        .load(glideUrl)
        .placeholder(placeholder)
        .apply(
            RequestOptions
                .circleCropTransform()
                .override((imageSize ?: DEFAULT_IMAGE_SIZE).dp(context))
        )
        .into(object : CustomViewTarget<TextView, Drawable>(this) {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                view.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null)
            }

            override fun onResourceCleared(placeholder: Drawable?) {
                view.setCompoundDrawablesWithIntrinsicBounds(placeholder, null, null, null)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                view.setCompoundDrawablesWithIntrinsicBounds(errorDrawable, null, null, null)
            }
        })
}

fun Int.dp(context: Context): Int =
    TypedValue
        .applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)
        .roundToInt()
