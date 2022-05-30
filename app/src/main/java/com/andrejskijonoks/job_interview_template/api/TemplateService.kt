package com.andrejskijonoks.job_interview_template.api

import retrofit2.http.GET

interface TemplateService {
    @GET("v2/currencies")
    suspend fun getCurrencies() : Any
}