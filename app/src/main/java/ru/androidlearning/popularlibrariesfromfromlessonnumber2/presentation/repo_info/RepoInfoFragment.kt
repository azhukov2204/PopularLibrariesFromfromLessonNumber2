package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.repo_info

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentRepoInfoBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoInfoEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.inject_templates.DaggerMvpFragment
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers
import javax.inject.Inject

private const val ARG_REPO_URL = "repository_url"

class RepoInfoFragment : DaggerMvpFragment(R.layout.fragment_repo_info), RepoInfoView, BackButtonListener {
    companion object {
        fun newInstance(repositoryUrl: String) =
            RepoInfoFragment().apply {
                arguments = bundleOf(ARG_REPO_URL to repositoryUrl)
            }
    }

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var schedulers: WorkSchedulers
    @Inject
    lateinit var gitHubUsersRepository: GitHubUsersRepository

    private val binding by viewBinding(FragmentRepoInfoBinding::bind)
    private val repositoryUrl: String? by lazy { arguments?.getString(ARG_REPO_URL) }
    private val presenter: RepoInfoPresenter by moxyPresenter {
        RepoInfoPresenter(
            gitHubUsersRepository = gitHubUsersRepository,
            repositoryUrl = repositoryUrl,
            schedulers = schedulers,
            router = router
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
