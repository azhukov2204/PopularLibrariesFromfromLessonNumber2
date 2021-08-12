package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.app.App
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentUserBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.repository.GitHubUsersRepositoryFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GithubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulersFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.utils.setStartDrawableCircleImageFromUrl

private const val ARG_USER_LOGIN = "user_login"

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView, BackButtonListener {
    private val binding by viewBinding(FragmentUserBinding::bind)

    private val login: String? by lazy { arguments?.getString(ARG_USER_LOGIN) }
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            gitHubUsersRepository = GitHubUsersRepositoryFactory.getRepository(),
            router = App.instance.router,
            userLogin = login,
            schedulers = WorkSchedulersFactory.create()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnButton.setOnClickListener { presenter.backPressed() }
    }

    override fun showUser(user: GithubUserEntity) {
        binding.selectedLogin.setStartDrawableCircleImageFromUrl(user.avatarUrl, binding.root.context.resources.getInteger(R.integer.avatar_image_size))
        binding.selectedLogin.text = user.login
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun showUserNotFound() {
        Toast.makeText(requireContext(), getString(R.string.user_not_found_message), Toast.LENGTH_LONG).show()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        fun newInstance(login: String): Fragment = UserFragment().apply {
            arguments = bundleOf(ARG_USER_LOGIN to login)
        }
    }
}
