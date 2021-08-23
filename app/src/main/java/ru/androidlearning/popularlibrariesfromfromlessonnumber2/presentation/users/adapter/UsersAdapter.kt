package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.TextViewItemBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.CachedImageLoader
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity

class UsersAdapter(
    private val itemClickListener: ItemClickListener,
    private val itemImageToCacheSaver: CachedImageLoader.ItemImageToCacheSaver
) : ListAdapter<GitHubUserEntity, UsersAdapter.UsersViewHolder>(UsersDiff) {
    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: TextViewItemBinding by viewBinding(TextViewItemBinding::bind)

        fun bind(user: GitHubUserEntity) {
            with(binding) {
                CachedImageLoader.setStartDrawableCircleImageFromUrl(textViewItem, user, itemImageToCacheSaver, root.context.resources.getInteger(R.integer.avatar_image_size))
                textViewItem.text = user.login
                itemView.setOnClickListener { itemClickListener.onUserClick(user) }
            }
        }
    }

    interface ItemClickListener {
        fun onUserClick(user: GitHubUserEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.text_view_item, parent, false)
        )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
