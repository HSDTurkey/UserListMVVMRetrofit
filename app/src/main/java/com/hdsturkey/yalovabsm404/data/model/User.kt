package com.hdsturkey.yalovabsm404.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//The following code defines a User data entity. Each instance of User represents a row in a user table in the app's database.


@Entity
data class User(
    @ColumnInfo(name = "name") val name: UserName,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "picture") val picture: UserPicture,
    @PrimaryKey(autoGenerate = true) val userId: Int = 0 // Each Room entity must define a primary key that uniquely identifies each row in the corresponding database table.
)
