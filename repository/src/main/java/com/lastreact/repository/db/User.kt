package com.lastreact.repository.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userid")
    val userId: Int,
    @ColumnInfo(name = "profileimage")
    val profileImage: String? = null,
    @ColumnInfo(name = "displayname")
    val displayName: String? = null,
    @ColumnInfo(name = "reputation")
    val reputation: Int? = null,
    @ColumnInfo(name = "badgegold")
    val badgegold: Int? = null,
    @ColumnInfo(name = "badgesilver")
    val badgeSilver: Int? = null,
    @ColumnInfo(name = "badgebronze")
    val badgeBronze: Int? = null,
    @ColumnInfo(name = "age")
    val age: String? = null,
    @ColumnInfo(name = "location")
    val location: String? = null,
    @ColumnInfo(name = "creationdate")
    val creationDate: Int? = null,
)