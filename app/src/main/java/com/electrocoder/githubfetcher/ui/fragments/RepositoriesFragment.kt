package com.electrocoder.githubfetcher.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ConcatAdapter
import com.electrocoder.githubfetcher.R
import com.electrocoder.githubfetcher.databinding.RepositoriesFragmentBinding
import com.electrocoder.githubfetcher.databinding.UserDetailsFragmentBinding
import com.electrocoder.githubfetcher.di.viewmodelfactory.ViewModelFactory
import com.electrocoder.githubfetcher.ui.adapters.ReposLoadStateAdapter
import com.electrocoder.githubfetcher.ui.adapters.ReposPagingAdapter
import com.electrocoder.githubfetcher.viewmodels.RepositoriesViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RepositoriesFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewmodel by viewModels<RepositoriesViewModel> { factory }

    val args: RepositoriesFragmentArgs by navArgs()

    private val reposAdapter = ReposPagingAdapter()
    private var recyclerViewAdapter = ConcatAdapter()

    var _binding: RepositoriesFragmentBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RepositoriesFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        binding.adapter = reposAdapter


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController()
        )

        binding.reposRecyclerview.apply {
            this.adapter = reposAdapter.withLoadStateFooter(
                footer = ReposLoadStateAdapter { reposAdapter.retry() }
            )
        }

        viewmodel.getRepositories(args.reposUrl).observe(viewLifecycleOwner, { pagingData ->
            reposAdapter.submitData(lifecycle, pagingData)
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}