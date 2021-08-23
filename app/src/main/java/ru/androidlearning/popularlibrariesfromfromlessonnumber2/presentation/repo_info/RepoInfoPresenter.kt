package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.repo_info

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoInfoEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers

class RepoInfoPresenter(
    private val gitHubUsersRepository: GitHubUsersRepository,
    private val repositoryUrl: String?,
    private val schedulers: WorkSchedulers,
    private val router: Router
) : MvpPresenter<RepoInfoView>() {
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repositoryUrl?.let {
            disposables +=
                gitHubUsersRepository.getUserRepositoryInfo(it)
                    .map(GitHubUserRepoInfoEntity.Mapper::map)
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .subscribe(
                        viewState::showRepoInfo,
                        viewState::showError
                    )
        }
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}