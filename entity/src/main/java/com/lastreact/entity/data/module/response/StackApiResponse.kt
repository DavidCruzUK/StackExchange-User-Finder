package com.lastreact.entity.data.module.response

import com.google.gson.annotations.SerializedName

data class StackApiResponse(

	@field:SerializedName("quota_max")
	val quotaMax: Int,

	val backoff: Int,

	@field:SerializedName("quota_remaining")
	val quotaRemaining: Int,

	@field:SerializedName("has_more")
	val hasMore: Boolean,

	val items: List<Items>
)

data class Items(

	@field:SerializedName("reputation_change_quarter")
	val reputationChangeQuarter: Int,

	val link: String,

	@field:SerializedName("last_access_date")
	val lastAccessDate: Int,

	val reputation: Int,

	@field:SerializedName("badge_counts")
	val badgeCounts: BadgeCounts,

	@field:SerializedName("creation_date")
	val creationDate: Int,

	@field:SerializedName("display_name")
	val displayName: String,

	@field:SerializedName("reputation_change_year")
	val reputationChangeYear: Int,

	@field:SerializedName("is_employee")
	val isEmployee: Boolean,

	@field:SerializedName("profile_image")
	val profileImage: String,

	@field:SerializedName("account_id")
	val accountId: Int,

	@field:SerializedName("user_type")
	val userType: String,

	@field:SerializedName("reputation_change_week")
	val reputationChangeWeek: Int,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("reputation_change_day")
	val reputationChangeDay: Int,

	val location: String,

	@field:SerializedName("reputation_change_month")
	val reputationChangeMonth: Int,

	@field:SerializedName("last_modified_date")
	val lastModifiedDate: Int,

	@field:SerializedName("website_url")
	val websiteUrl: String
)

data class BadgeCounts(

	val gold: Int,

	val silver: Int,

	val bronze: Int
)
