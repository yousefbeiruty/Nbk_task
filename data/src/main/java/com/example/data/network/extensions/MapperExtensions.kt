package com.example.data.network.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import java.lang.reflect.Type

inline fun <reified T> ResponseBody.getModel(): T? {
    return Gson().fromJson(this.string(), type<T>())
}

inline fun <reified T> type(): Type = object : TypeToken<T>() {}.type