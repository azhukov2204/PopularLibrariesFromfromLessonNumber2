package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentUserBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.CachedImageLoader
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.inject_templates.DaggerMvpFragment
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user.adapter.UserReposAdapter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers
import javax.inject.Inject

private const val ARG_USER_LOGIN = "user_login"

class UserFragment : DaggerMvpFragment(R.layout.fragment_user), UserView, BackButtonListener, UserReposAdapter.ItemClickListener {
    companion object {
        fun newInstance(login: String): Fragment = UserFragment().apply {
            arguments = bundleOf(ARG_USER_LOGIN to login)
        }
    }

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var schedulers: WorkSchedulers
    @Inject
    lateinit var gitHubUsersRepository: GitHubUsersRepository

    private val binding by viewBinding(FragmentUserBinding::bind)
    private val userReposAdapter: UserReposAdapter = UserReposAdapter(this)
    private val login: String? by lazy { arguments?.getString(ARG_USER_LOGIN) }
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            gitHubUsersRepository = gitHubUsersRepository,
            router = router,
            userLogin = login,
            schedulers = schedulers
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnButton.setOnClickListener { presenter.backPressed() }
        binding.userReposRecyclerView.adapter = userReposAdapter
    }

    override fun showUser(user: GitHubUserEntity) {
        CachedImageLoader.setStartDrawableCircleImageFromUrl(binding.selectedLogin, user, null, binding.root.context.resources.getInteger(R.integer.avatar_image_size))
        binding.selectedLogin.text = user.login
    }

    override fun loadingLayoutIsVisible(isVisible: Boolean) {
        binding.loadingLayout.root.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showRepos(gitHubUserRepos: List<GitHubUserRepoEntity>) {
        userReposAdapter.submitList(gitHubUserRepos)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun onUserRepoClick(gitHubUserRepoEntity: GitHubUserRepoEntity) {
        presenter.displayRepo(gitHubUserRepoEntity.repoUrl)
    }

    override fun backPressed() = presenter.backPressed()
}
