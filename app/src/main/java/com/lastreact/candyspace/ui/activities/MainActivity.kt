package com.lastreact.candyspace.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.lastreact.candyspace.base.BaseActivity
import com.lastreact.candyspace.databinding.ActivityMainBinding
import com.lastreact.candyspace.viewmodel.MainViewModel
import com.lastreact.entity.data.module.response.StackApiResponse
import com.lastreact.repository.Repository
import com.lastreact.service.di.ResponseHandler
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(), ResponseHandler {

    @Inject
    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        lifecycleScope.launchWhenStarted {
            mainViewModel.getUsers(this@MainActivity)
        }
    }

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onSuccessResponse(response: StackApiResponse) {
        Log.i("TEST", "onSuccessResponse: $response")
    }

    override fun onErrorResponse(error: Throwable) {
        Log.i("TEST", "onSuccessResponse: $error")
    }
}