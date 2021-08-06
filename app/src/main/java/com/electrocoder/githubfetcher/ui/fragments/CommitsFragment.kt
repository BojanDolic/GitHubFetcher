package com.electrocoder.githubfetcher.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import com.electrocoder.githubfetcher.R
import com.electrocoder.githubfetcher.databinding.CommitsFragmentBinding
import com.electrocoder.githubfetcher.di.viewmodelfactory.ViewModelFactory
import com.electrocoder.githubfetcher.ui.adapters.CommitsPagingAdapter
import com.electrocoder.githubfetcher.ui.adapters.ReposLoadStateAdapter
import com.electrocoder.githubfetcher.viewmodels.CommitsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class CommitsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: CommitsViewModel by viewModels { factory }

    private val args: CommitsFragmentArgs by navArgs()

    private var adapter = CommitsPagingAdapter()
    private var observableAdapter: ObservableField<ConcatAdapter> = ObservableField(
        adapter.withLoadStateFooter(
            footer = ReposLoadStateAdapter { adapter.retry() }
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CommitsFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        binding.adapter = observableAdapter.get()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*observableAdapter.set(
            adapter.withLoadStateFooter(
                footer = ReposLoadStateAdapter { adapter.retry() }
            )
        )*/

        if(viewModel.commits.value == null)
            viewModel.getCommits(args.commitsUrl)

        viewModel.commits.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }


    }

}