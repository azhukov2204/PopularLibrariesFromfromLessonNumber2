package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.ItemUserBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GithubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.utils.setStartDrawableCircleImageFromUrl

class UsersAdapter(val itemClickListener: ItemClickListener) : ListAdapter<GithubUserEntity, UsersAdapter.UsersViewHolder>(UsersDiff) {
    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemUserBinding by viewBinding(ItemUserBinding::bind)

        fun bind(user: GithubUserEntity) {
            with(binding) {
                userLogin.setStartDrawableCircleImageFromUrl(user.avatarUrl, root.context.resources.getInteger(R.integer.avatar_image_size) )
                userLogin.text = user.login
                itemView.setOnClickListener { itemClickListener.onUserClick(user) }
            }
        }
    }

    interface ItemClickListener {
        fun onUserClick(user: GithubUserEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
