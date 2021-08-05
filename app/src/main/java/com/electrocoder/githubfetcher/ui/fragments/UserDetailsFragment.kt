package com.electrocoder.githubfetcher.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.electrocoder.githubfetcher.R
import com.electrocoder.githubfetcher.databinding.UserDetailsFragmentBinding
import com.electrocoder.githubfetcher.di.viewmodelfactory.ViewModelFactory
import com.electrocoder.githubfetcher.viewmodels.UserDetailsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private const val TAG = "UserDetailsFragment"

class UserDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<UserDetailsViewModel> { factory }

    private val args: UserDetailsFragmentArgs by navArgs()

    var _binding: UserDetailsFragmentBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserDetailsFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(binding.toolbar, findNavController())

        viewModel._user.observe(viewLifecycleOwner) { user ->
            binding.user = user
        }

        if(viewModel.user == null)
            viewModel.queryByUserName(args.username)

        binding.userReposBtn.setOnClickListener {
            findNavController().navigate(
                UserDetailsFragmentDirections.actionUserDetailsFragmentToRepositoriesFragment(
                    viewModel.user?.reposUrl ?: ""
                )
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}