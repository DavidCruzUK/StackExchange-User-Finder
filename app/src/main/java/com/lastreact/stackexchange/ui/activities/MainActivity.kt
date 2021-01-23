package com.lastreact.stackexchange.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.service.di.ResponseHandler
import com.lastreact.stackexchange.base.BaseActivity
import com.lastreact.stackexchange.databinding.ActivityMainBinding
import com.lastreact.stackexchange.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(), ResponseHandler {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

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
        Log.i("TEST", "onSuccessResponse: $response")
        viewModel.showProgressBar(false)
        runOnUiThread {
            binding.hello.text = response[0].displayName
            binding.hello.setOnClickListener {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtras(bundleOf(DetailActivity.USER_ID_KEY to response[0].userId))
                }
                startActivity(intent)
            }
        }

    }

    override fun onErrorResponse(error: Throwable) {
        Log.i("TEST", "onSuccessResponse: $error")
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