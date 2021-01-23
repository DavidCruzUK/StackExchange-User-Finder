package com.lastreact.stackexchange.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.lastreact.stackexchange.base.BaseActivity
import com.lastreact.stackexchange.databinding.ActivityMainBinding
import com.lastreact.stackexchange.viewmodel.MainViewModel
import com.lastreact.entity.data.module.response.Items
import com.lastreact.service.di.ResponseHandler
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(), ResponseHandler {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        lifecycleScope.launchWhenStarted {
            viewModel.getUsers(this@MainActivity)
        }
    }

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onSuccessResponse(response: List<Items>) {
        Log.i("TEST", "onSuccessResponse: $response")
    }

    override fun onErrorResponse(error: Throwable) {
        Log.i("TEST", "onSuccessResponse: $error")
    }
}