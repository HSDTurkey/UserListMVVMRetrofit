package com.hdsturkey.yalovabsm404.utils

import com.hdsturkey.yalovabsm404.data.remote.Services
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    private lateinit var services: Services

    fun getServices(): Services {
        if (this::services.isInitialized) {
            return services
        } else {
            services = createRetrofitInstance(Services::class.java)
            return services
        }
    }

    private fun createRetrofitInstance(java: Class<Services>): Services {
        val client = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val services = retrofit.create(Services::class.java)
        return services
    }
}