package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.app.App
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentUsersBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GithubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users.adapter.UsersAdapter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.repository.GitHubUsersRepositoryFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulersFactory

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener, UsersAdapter.ItemClickListener {
    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            usersRepository = GitHubUsersRepositoryFactory.getRepository(),
            router = App.instance.router,
            schedulers = WorkSchedulersFactory.create()
        )
    }
    private val binding by viewBinding(FragmentUsersBinding::bind)
    private val usersAdapter: UsersAdapter = UsersAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.usersRecyclerView.adapter = usersAdapter
    }

    override fun showUsers(users: List<GithubUserEntity>) {
        usersAdapter.submitList(users)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun onUserClick(user: GithubUserEntity) {
        presenter.displayUser(user)
    }

    override fun backPressed() = presenter.backPressed()
}
