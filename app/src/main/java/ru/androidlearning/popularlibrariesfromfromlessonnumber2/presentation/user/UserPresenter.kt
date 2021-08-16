package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.RepoInfoFragmentScreen
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers

class UserPresenter(
    private val gitHubUsersRepository: GitHubUsersRepository,
    private val router: Router,
    private val userLogin: String?,
    private val schedulers: WorkSchedulers
) : MvpPresenter<UserView>() {
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserLoginData()
    }

    private fun loadUserLoginData() {
        userLogin?.let { login ->
            disposables +=
                gitHubUsersRepository.getUserByLogin(login)
                    .map(GitHubUserEntity.Mapper::map)
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .subscribe(
                        this::doOnSuccessLoadUserLoginData,
                        viewState::showError
                    )
        }
    }

    private fun doOnSuccessLoadUserLoginData(user: GitHubUserEntity) {
        viewState.showUser(user)
        loadUserReposData(user)
    }

    private fun loadUserReposData(user: GitHubUserEntity) {
        viewState.loadingLayoutIsVisible(true)
        user.userReposUrl?.let {
            disposables += gitHubUsersRepository.getUserRepositories(it)
                .map { gitHubUserRepos -> gitHubUserRepos.map(GitHubUserRepoEntity.Mapper::map) }
                .observeOn(schedulers.threadMain())
                .subscribeOn(schedulers.threadIO())
                .subscribe(
                    this::doOnSuccessLoadUserReposData,
                    this::doOnErrorLoadUserReposData
                )
        }
    }

    private fun doOnSuccessLoadUserReposData(gitHubUserRepos: List<GitHubUserRepoEntity>) {
        viewState.showRepos(gitHubUserRepos)
        viewState.loadingLayoutIsVisible(false)
    }

    private fun doOnErrorLoadUserReposData(error: Throwable) {
        viewState.showError(error)
        viewState.loadingLayoutIsVisible(false)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun displayRepo(repoUrl: String) {
        router.navigateTo(RepoInfoFragmentScreen(repoUrl))
    }
}
