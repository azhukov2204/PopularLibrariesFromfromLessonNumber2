package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details

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
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentLoginDetailsBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GitHubUsersRepositoryFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulersFactory

private const val ARG_USER_ID = "user_id_key"

class LoginDetailsFragment : MvpAppCompatFragment(R.layout.fragment_login_details), LoginDetailsView, BackButtonListener {
    private val binding by viewBinding(FragmentLoginDetailsBinding::bind)

    private val userId: Long? by lazy { arguments?.getLong(ARG_USER_ID) }
    private val presenter: LoginDetailsPresenter by moxyPresenter {
        LoginDetailsPresenter(
            gitHubUsersRepository = GitHubUsersRepositoryFactory.create(),
            router = App.instance.router,
            userId = userId,
            schedulers = WorkSchedulersFactory.create()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnButton.setOnClickListener { presenter.backPressed() }
    }

    override fun showUser(login: String) {
        binding.selectedLogin.text = login
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun showUserNotFound() {
        Toast.makeText(requireContext(), getString(R.string.user_not_found_message), Toast.LENGTH_LONG).show()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        fun newInstance(userId: Long): Fragment = LoginDetailsFragment().apply {
            arguments = bundleOf(ARG_USER_ID to userId)
        }
    }
}
