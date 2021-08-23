package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.TypedValue
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import java.io.File
import kotlin.math.roundToInt

object CachedImageLoader {
    private const val DEFAULT_IMAGE_SIZE = 50

    interface ItemImageToCacheSaver {
        fun saveItemImageToCache(bitmap: Bitmap, user: GitHubUserEntity)
    }

    fun setStartDrawableCircleImageFromUrl(
        textView: TextView,
        user: GitHubUserEntity,
        itemImageToCacheSaver: ItemImageToCacheSaver? = null,
        imageSize: Int? = null,
        placeholder: Int = 0
    ) {
        val context = textView.context
        val requestBuilder = user.avatarLocalPath?.let { avatarLocalPath -> useCache(avatarLocalPath, context, imageSize, placeholder) }
            ?: makeCache(user, context, itemImageToCacheSaver, imageSize, placeholder)

        requestBuilder.into(object : CustomViewTarget<TextView, Drawable>(textView) {
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

    private fun useCache(avatarLocalPath: String, context: Context, imageSize: Int?, placeholder: Int): RequestBuilder<Drawable> {
        println("loadCachedImage")
        val glideUri = Uri.fromFile(File(avatarLocalPath))
        return Glide.with(context)
            .load(glideUri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(placeholder)
            .apply(
                RequestOptions
                    .circleCropTransform()
                    .override((imageSize ?: DEFAULT_IMAGE_SIZE).dp(context))
            )
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun makeCache(
        user: GitHubUserEntity,
        context: Context,
        itemImageToCacheSaver: ItemImageToCacheSaver?,
        imageSize: Int?,
        placeholder: Int
    ): RequestBuilder<Drawable> {
        println("makeCache")

        val glideUrl = if (user.avatarUrl.isNullOrBlank()) placeholder else GlideUrl(user.avatarUrl)
        return Glide.with(context)
            .load(glideUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(placeholder)
            .apply(
                RequestOptions
                    .circleCropTransform()
                    .override((imageSize ?: DEFAULT_IMAGE_SIZE).dp(context))
            )
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        resource as BitmapDrawable
                        itemImageToCacheSaver?.saveItemImageToCache(resource.bitmap, user)
                        return false
                    }
                }
            )
    }

    private fun Int.dp(context: Context): Int =
        TypedValue
            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)
            .roundToInt()
}
