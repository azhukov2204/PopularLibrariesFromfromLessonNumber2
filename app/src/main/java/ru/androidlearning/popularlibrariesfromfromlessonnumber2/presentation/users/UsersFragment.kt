package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.app.App
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.image_cache.ImageToCacheSaverFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentUsersBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users.adapter.UsersAdapter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepositoryFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.CachedImageLoader
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulersFactory

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener, UsersAdapter.ItemClickListener, CachedImageLoader.ItemImageToCacheSaver {
    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            usersRepository = GitHubUsersRepositoryFactory.create(requireContext()),
            router = App.instance.router,
            schedulers = WorkSchedulersFactory.create(),
            imageToCacheSaver = ImageToCacheSaverFactory.create(requireContext())
        )
    }
    private val binding by viewBinding(FragmentUsersBinding::bind)
    private val usersAdapter: UsersAdapter = UsersAdapter(this, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.usersRecyclerView.adapter = usersAdapter
    }

    override fun showUsers(users: List<GitHubUserEntity>) {
        usersAdapter.submitList(users)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun onUserClick(user: GitHubUserEntity) {
        presenter.displayUser(user)
    }

    override fun saveItemImageToCache(bitmap: Bitmap, user: GitHubUserEntity) {
        presenter.saveItemImageToCache(bitmap, user)
    }

    override fun backPressed() = presenter.backPressed()
}
