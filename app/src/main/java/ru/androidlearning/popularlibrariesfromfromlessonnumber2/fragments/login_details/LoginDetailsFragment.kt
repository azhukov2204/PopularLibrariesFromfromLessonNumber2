package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.app.App
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.FragmentLoginDetailsBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GithubUsersRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.BackButtonListener

private const val ARG_POSITION = "position_key"

class LoginDetailsFragment : MvpAppCompatFragment(), LoginDetailsView, BackButtonListener {
    private var _binding: FragmentLoginDetailsBinding? = null
    private val binding get() = _binding!!
    private var position: Int? = null
    private val presenter: LoginDetailsPresenter by moxyPresenter { LoginDetailsPresenter(GithubUsersRepo(), App.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
        }
        presenter.setPosition(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnButton.setOnClickListener { presenter.backPressed() }
    }

    override fun renderData(login: String) {
        binding.selectedLogin.text = login
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        @JvmStatic
        fun newInstance(position: Int) = LoginDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_POSITION, position)
            }
        }
    }
}
