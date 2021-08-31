package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.image_cache.ImageToCacheSaver
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentUsersBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.CachedImageLoader
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.inject_templates.DaggerMvpFragment
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users.adapter.UsersAdapter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers
import javax.inject.Inject

class UsersFragment : DaggerMvpFragment(R.layout.fragment_users), UsersView, BackButtonListener, UsersAdapter.ItemClickListener, CachedImageLoader.ItemImageToCacheSaver {
    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    @Inject
    lateinit var schedulers: WorkSchedulers
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var usersRepository: GitHubUsersRepository
    @Inject
    lateinit var imageToCacheSaver: ImageToCacheSaver

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            usersRepository = usersRepository,
            router = router,
            schedulers = schedulers,
            imageToCacheSaver = imageToCacheSaver
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
