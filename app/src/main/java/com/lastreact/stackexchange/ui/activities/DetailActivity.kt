package com.lastreact.stackexchange.ui.activities

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.stackexchange.base.BaseActivity
import com.lastreact.stackexchange.databinding.ActivityDetailBinding
import com.lastreact.stackexchange.viewmodel.DetailViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    companion object {
        const val USER_ID_KEY = "DetailActivity::userid"
    }

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        lifecycleScope.launchWhenStarted {
            intent?.extras?.getInt(USER_ID_KEY)?.let {
                viewModel.retrieveUserById(it)
            }
            viewModel.model.collect(::onModeCollect)
        }

    }

    override fun createViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private fun onModeCollect(model: DetailViewModel.DetailModel) {
        when(model) {
            is DetailViewModel.DetailModel.RetrieveUserById -> populateUserData(model.user)
        }
    }

    private fun populateUserData(user: UserItem) {
        binding.userName.text = user.profileImage
    }
}