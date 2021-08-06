package com.electrocoder.githubfetcher.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.electrocoder.githubfetcher.DaggerApplication
import com.electrocoder.githubfetcher.R
import com.electrocoder.githubfetcher.api.GitHubApi
import com.electrocoder.githubfetcher.api.RetrofitClient
import com.electrocoder.githubfetcher.databinding.MainFragmentBinding
import com.electrocoder.githubfetcher.di.viewmodelfactory.ViewModelFactory
import com.electrocoder.githubfetcher.models.ApiResponse
import com.electrocoder.githubfetcher.models.User
import com.electrocoder.githubfetcher.repository.Repository
import com.electrocoder.githubfetcher.ui.adapters.UserListAdapter
import com.electrocoder.githubfetcher.viewmodels.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "MainFragment"

class MainFragment : DaggerFragment(), UserListAdapter.OnUserClicked {

    @Inject
    lateinit var providerFactory: ViewModelFactory

    val viewModel by viewModels<MainViewModel> { providerFactory }

    private var emptyList: ObservableBoolean = ObservableBoolean(false)

    private var adapter = UserListAdapter().apply {
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
        setUserClickListener(this@MainFragment)
    }

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
        binding.adapter = adapter
        binding.emptylist = emptyList

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.usersList.observe(viewLifecycleOwner, { response ->

            if(response != null) {
                val users = response.users
                adapter.submitList(users)
                emptyList.set(users.isEmpty())

                Log.d(TAG, "onViewCreated: IS LIST EMPTY $emptyList")
            } else {

            }


        })

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