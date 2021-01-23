package com.lastreact.repository.data.mapper

import com.lastreact.entity.data.module.response.BadgeCounts
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.entity.data.module.response.StackApiResponse
import com.lastreact.repository.db.User


fun StackApiResponse.mapToUserDao(): List<User> {
    return items.map {
        User(
            it.userId,
            it.profileImage,
            it.displayName,
            it.reputation,
            it.badgeCounts?.gold,
            it.badgeCounts?.silver,
            it.badgeCounts?.bronze,
            it.age,
            it.location,
            it.creationDate,
        )
    }
}

fun List<User>.mapToPresent(): List<UserItem> {
    return map { it.mapToPresent() }
}

fun User.mapToPresent(): UserItem {
    return UserItem(
        userId = userId,
        profileImage = profileImage,
        displayName = displayName,
        reputation = reputation,
        badgeCounts = BadgeCounts(
            gold = badgegold,
            silver = badgeSilver,
            bronze = badgeBronze,
        ),
        age = age,
        location = location,
        creationDate = creationDate
    )
}