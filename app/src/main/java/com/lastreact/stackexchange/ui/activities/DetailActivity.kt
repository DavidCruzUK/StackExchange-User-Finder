package com.lastreact.stackexchange.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.stackexchange.R
import com.lastreact.stackexchange.base.BaseActivity
import com.lastreact.stackexchange.databinding.ActivityDetailBinding
import com.lastreact.stackexchange.extensions.convertLongToTime
import com.lastreact.stackexchange.extensions.loadUrl
import com.lastreact.stackexchange.viewmodel.DetailViewModel
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*
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
        setActionBarBackIcon()
    }

    override fun createViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private fun onModeCollect(model: DetailViewModel.DetailModel) {
        when (model) {
            is DetailViewModel.DetailModel.RetrieveUserById -> populateUserData(model.user)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setActionBarBackIcon() {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun populateUserData(user: UserItem) {
        supportActionBar?.title = user.displayName
        with(binding) {
            user.profileImage?.let { avatarIv.loadUrl(it) }
            userNameTv.text = user.displayName
            reputationTv.text = user.reputation.toString()
            goldBadgeCountTv.text = user.badgeCounts?.gold.toString()
            silverBadgeCountTv.text = user.badgeCounts?.silver.toString()
            bronzeBadgeCount.text = user.badgeCounts?.bronze.toString()
            locationTv.text = user.location
            ageTv.text = user.age
            creationDateTv.text = user.creationDate?.toLong()?.convertLongToTime()
        }
    }
}