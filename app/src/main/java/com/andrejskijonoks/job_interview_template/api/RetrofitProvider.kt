package com.andrejskijonoks.job_interview_template.api

import com.andrejskijonoks.job_interview_template.BuildConfig.API_KEY
import com.andrejskijonoks.job_interview_template.base.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    val templateService: OpticsPlanetService by lazy {

        val httpClient = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("x-apikey", API_KEY)
                    .build()
            )
        }

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()

        return@lazy retrofit.create(OpticsPlanetService::class.java)
    }
}