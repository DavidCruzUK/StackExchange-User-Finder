package com.lastreact.entity.data.module.response

import com.google.gson.annotations.SerializedName

data class StackApiResponse(
    val items: List<Items>
)

data class Items(

    val reputation: Int? = null,

    @field:SerializedName("badge_counts")
    val badgeCounts: BadgeCounts? = null,

    @field:SerializedName("creation_date")
    val creationDate: Int? = null,

    @field:SerializedName("display_name")
    val displayName: String? = null,

    @field:SerializedName("profile_image")
    val profileImage: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    val age: String? = null,

    val location: String? = null,
)

data class BadgeCounts(
    val gold: Int? = null,
    val silver: Int? = null,
    val bronze: Int? = null,
)
