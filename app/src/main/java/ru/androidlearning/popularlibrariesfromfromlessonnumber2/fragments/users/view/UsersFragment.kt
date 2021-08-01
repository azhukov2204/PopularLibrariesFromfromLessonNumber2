package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.app.App
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentUsersBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.presenter.UsersPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GithubUsersRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.AndroidScreens
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        @JvmStatic
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens()) }
    private var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}
