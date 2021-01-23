package com.lastreact.repository.data.mapper

import com.lastreact.entity.data.module.response.BadgeCounts
import com.lastreact.entity.data.module.response.Items
import com.lastreact.entity.data.module.response.StackApiResponse
import com.lastreact.repository.db.User


fun StackApiResponse.mapToUserDao(): List<User> {
    return items.map { User(
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
    ) }
}

fun List<User>.mapToPresent(): List<Items> {
    return map {
        Items(
            userId = it.userId,
            profileImage = it.profileImage,
            displayName = it.displayName,
            reputation = it.reputation,
            badgeCounts = BadgeCounts(
                gold = it.badgegold,
                silver = it.badgeSilver,
                bronze = it.badgeBronze,
            ),
            age = it.age,
            location = it.location,
            creationDate = it.creationDate
        )
    }
}