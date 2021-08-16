package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.repo_info

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.app.App
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepositoryFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentRepoInfoBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoInfoEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulersFactory

private const val ARG_REPO_URL = "repository_url"

class RepoInfoFragment : MvpAppCompatFragment(R.layout.fragment_repo_info), RepoInfoView, BackButtonListener {
    companion object {
        fun newInstance(repositoryUrl: String) =
            RepoInfoFragment().apply {
                arguments = bundleOf(ARG_REPO_URL to repositoryUrl)
            }
    }

    private val binding by viewBinding(FragmentRepoInfoBinding::bind)
    private val repositoryUrl: String? by lazy { arguments?.getString(ARG_REPO_URL) }
    private val presenter: RepoInfoPresenter by moxyPresenter {
        RepoInfoPresenter(
            gitHubUsersRepository = GitHubUsersRepositoryFactory.create(requireContext()),
            repositoryUrl = repositoryUrl,
            schedulers = WorkSchedulersFactory.create(),
            router = App.instance.router
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnButton.setOnClickListener { presenter.backPressed() }
    }

    override fun showRepoInfo(gitHubUserRepoInfoEntity: GitHubUserRepoInfoEntity) {
        with(binding) {
            name.text = gitHubUserRepoInfoEntity.name
            fullName.text = gitHubUserRepoInfoEntity.fullName
            size.text = gitHubUserRepoInfoEntity.size.toString()
            watchersCount.text = gitHubUserRepoInfoEntity.watchersCount.toString()
            language.text = gitHubUserRepoInfoEntity.language
            forksCount.text = gitHubUserRepoInfoEntity.forksCount.toString()
            forks.text = gitHubUserRepoInfoEntity.forks.toString()
        }
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed(): Boolean =
        presenter.backPressed()
}
