package com.electrocoder.githubfetcher.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.electrocoder.githubfetcher.R
import com.electrocoder.githubfetcher.databinding.MainFragmentBinding
import com.electrocoder.githubfetcher.di.viewmodelfactory.ViewModelFactory
import com.electrocoder.githubfetcher.models.User
import com.electrocoder.githubfetcher.ui.adapters.ReposLoadStateAdapter
import com.electrocoder.githubfetcher.ui.adapters.UsersPagingAdapter
import com.electrocoder.githubfetcher.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private const val TAG = "MainFragment"

class MainFragment : DaggerFragment(), UsersPagingAdapter.OnUserClicked {

    @Inject
    lateinit var providerFactory: ViewModelFactory

    val viewModel by viewModels<MainViewModel> { providerFactory }

    private var emptyListBool: ObservableBoolean = ObservableBoolean(false)
    private var loadingData: ObservableBoolean = ObservableBoolean(false)

    private var adapter = UsersPagingAdapter().apply {
        setUserClickListener(this@MainFragment)
    }

    private val recyclerAdapter: ObservableField<ConcatAdapter> = ObservableField(
        adapter.withLoadStateFooter(
            footer = ReposLoadStateAdapter { adapter.retry() }
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        binding.viewmodel = viewModel
        binding.adapter = recyclerAdapter.get()
        binding.emptylist = emptyListBool
        binding.loadingData = loadingData

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.usersList.observe(viewLifecycleOwner, { pagingData ->
            adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })

        adapter.addLoadStateListener { combinedLoadStates ->
            // Check to see if response is empty which means that no users were found
            emptyListBool.set(
                combinedLoadStates.source.refresh is LoadState.NotLoading
                        && combinedLoadStates.append.endOfPaginationReached
                        && adapter.itemCount == 0
            )

            // Show loading bar while data is being fetched
            loadingData.set(
                combinedLoadStates.refresh is LoadState.Loading
                        && adapter.itemCount == 0
            )

            // If error occurs, show snackbar with retry button
            if(combinedLoadStates.refresh is LoadState.Error
                && !combinedLoadStates.refresh.endOfPaginationReached)
                    Snackbar.make(
                        view,
                        getString(R.string.loading_data_error_text),
                        Snackbar.LENGTH_LONG
                    ).apply {
                        setAction(
                            "Retry"
                        ) {
                            adapter.retry()
                            this.dismiss()
                        }
                    }.show()

        }

    }

    /**
     * Function called when user clicks on user element inside recyclerview
     * @param user user object
     */
    override fun onUserClicked(user: User) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToUserDetailsFragment(
                user.name
            )
        )
    }


}