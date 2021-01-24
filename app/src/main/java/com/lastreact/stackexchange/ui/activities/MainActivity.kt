package com.lastreact.stackexchange.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.service.di.ResponseHandler
import com.lastreact.stackexchange.base.BaseActivity
import com.lastreact.stackexchange.databinding.ActivityMainBinding
import com.lastreact.stackexchange.extensions.hideKeyboard
import com.lastreact.stackexchange.ui.adapter.UserListAdapter
import com.lastreact.stackexchange.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding>(), ResponseHandler {

    @Inject
    lateinit var viewModel: MainViewModel

    private val adapter = UserListAdapter { id ->
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtras(bundleOf(DetailActivity.USER_ID_KEY to id))
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setupRecyclerView()
        setClickListeners()

        lifecycleScope.launchWhenStarted {
            viewModel.showProgressBar(true)
            viewModel.getUsers(this@MainActivity)
            viewModel.model.collect(::onCollect)
        }
    }

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onSuccessResponse(response: List<UserItem>) {
        viewModel.showProgressBar(false)
        viewModel.userItems = ArrayList(response)
        runOnUiThread {
            adapter.items = response
        }
    }

    override fun onErrorResponse(error: Throwable) {
        viewModel.showProgressBar(false)
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                adapter.items = viewModel.getUsers()
                viewModel.userItems = ArrayList(adapter.items)
                displayErrorTextWhenNoUserItems()
            }
        }
    }

    private fun onCollect(uiModel: MainViewModel.UiModel) {
        when (uiModel) {
            is MainViewModel.UiModel.ShowProgress -> showProgressBar(uiModel.show)
        }
    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setClickListeners() {
        binding.searchButton.setOnClickListener {
            hideKeyboard()
            onSearchButtonPressed()
        }
    }

    private fun onSearchButtonPressed() {
        removeSearchFocusAndErrorDisplay()
        val text = binding.textInputEditText.text.toString().trim()
        val filteredList = ArrayList<UserItem>()
        if (text.isBlank()) {
            filteredList.addAll(viewModel.userItems)
        } else {
            searchTextIsNotEmpty(filteredList, text)
        }
        adapter.items = filteredList
        displayErrorTextWhenNoUserItems()
    }

    private fun displayErrorTextWhenNoUserItems() {
        if (adapter.items.isEmpty()) {
            binding.noPreviousDataTv.visibility = View.VISIBLE
        }
    }

    private fun searchTextIsNotEmpty(
        filteredList: ArrayList<UserItem>,
        text: String
    ) {
        filteredList.addAll(viewModel.getFilteredItems(text))
    }

    private fun removeSearchFocusAndErrorDisplay() {
        binding.noPreviousDataTv.visibility = View.GONE
        binding.textInputEditText.clearFocus()
    }

    private fun setupRecyclerView() {
        binding.usersListRv.layoutManager = LinearLayoutManager(this)
        binding.usersListRv.setHasFixedSize(true)
        binding.usersListRv.adapter = adapter
    }

}