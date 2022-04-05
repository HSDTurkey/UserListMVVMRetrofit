package com.hdsturkey.yalovabsm404.data.local.type_converters

import androidx.room.TypeConverter
import com.hdsturkey.yalovabsm404.data.model.UserName
import org.json.JSONObject

class NameTypeConverter {
    @TypeConverter
    fun fromSource(name: UserName): String {
        return JSONObject().apply {
            put("title", name.title)
            put("first", name.first)
            put("last", name.last)
        }.toString()
    }

    @TypeConverter
    fun toSource(nameJson: String): UserName {
        val json = JSONObject(nameJson)
        return UserName(
            json.getString("title"),
            json.getString("first"),
            json.getString("last")
        )
    }
}