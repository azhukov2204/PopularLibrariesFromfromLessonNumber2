package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users

import android.graphics.Bitmap
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.image_cache.ImageToCacheSaver
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.UserFragmentScreen
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers

class UsersPresenter(
    private val usersRepository: GitHubUsersRepository,
    private val router: Router,
    private val schedulers: WorkSchedulers,
    private val imageToCacheSaver: ImageToCacheSaver
) : MvpPresenter<UsersView>() {
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUsersList()
    }

    private fun loadUsersList() {
        disposables +=
            usersRepository.getUsers()
                .map { users -> users.map { GitHubUserEntity.Mapper.map(it) } }
                .observeOn(schedulers.threadMain())
                .subscribeOn(schedulers.threadIO())
                .subscribe(
                    viewState::showUsers,
                    viewState::showError
                )
    }

    fun displayUser(user: GitHubUserEntity) {
        router.navigateTo(UserFragmentScreen(user.login))
    }

    fun saveItemImageToCache(bitmap: Bitmap, user: GitHubUserEntity) {
        disposables +=
            imageToCacheSaver.save(user.userId, bitmap)
                .observeOn(schedulers.threadMain())
                .subscribeOn(schedulers.threadIO())
                .subscribe(
                    { avatarLocalPath -> user.avatarLocalPath = avatarLocalPath },
                    viewState::showError
                )
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
