package com.andrejskijonoks.job_interview_template.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private const val BASE_URL = "https://api.coinbase.com/"

    val templateService: TemplateService by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return@lazy retrofit.create(TemplateService::class.java)
    }
}