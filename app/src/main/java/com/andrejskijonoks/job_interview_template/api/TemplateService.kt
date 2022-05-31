package com.andrejskijonoks.job_interview_template.api

import com.andrejskijonoks.job_interview_template.models.TemplateDataHolder
import retrofit2.http.GET

interface TemplateService {
    @GET("v2/currencies")
    suspend fun getTemplateDataHolder() : TemplateDataHolder
}