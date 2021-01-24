package com.lastreact.stackexchange.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.service.di.ResponseHandler
import com.lastreact.stackexchange.base.BaseActivity
import com.lastreact.stackexchange.databinding.ActivityMainBinding
import com.lastreact.stackexchange.ui.adapter.UserListAdapter
import com.lastreact.stackexchange.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

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
        binding.usersListRv.layoutManager = LinearLayoutManager(this)
        binding.usersListRv.setHasFixedSize(true)
        binding.usersListRv.adapter = adapter

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
        runOnUiThread {
            adapter.items = response
        }
    }

    override fun onErrorResponse(error: Throwable) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                adapter.items = viewModel.getUsers()
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

}