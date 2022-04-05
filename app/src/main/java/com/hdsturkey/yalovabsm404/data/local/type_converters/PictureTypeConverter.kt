package com.hdsturkey.yalovabsm404.data.local.type_converters

import androidx.room.TypeConverter
import com.hdsturkey.yalovabsm404.data.model.UserPicture
import org.json.JSONObject

class UserPictureTypeConverter {
    @TypeConverter
    fun fromSource(picture: UserPicture): String {
        return JSONObject().apply {
            put("large", picture.large)
            put("medium", picture.medium)
            put("thumbnail", picture.thumbnail)
        }.toString()
    }

    @TypeConverter
    fun toSource(pictureJson: String): UserPicture {
        val json = JSONObject(pictureJson)
        return UserPicture(
            json.getString("large"),
            json.getString("medium"),
            json.getString("thumbnail")
        )
    }
}