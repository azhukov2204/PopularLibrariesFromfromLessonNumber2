package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.repo_info

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoInfoEntity

interface RepoInfoView : MvpView {
    @AddToEndSingle
    fun showRepoInfo(gitHubUserRepoInfoEntity: GitHubUserRepoInfoEntity)

    @OneExecution
    fun showError(error: Throwable)
}
