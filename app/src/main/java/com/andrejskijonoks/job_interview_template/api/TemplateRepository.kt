package com.andrejskijonoks.job_interview_template.api

import com.andrejskijonoks.job_interview_template.models.TemplateData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TemplateRepository : KoinComponent {
    private val service: TemplateService by inject()

    suspend fun getTemplateData() : List<TemplateData> {
        return service.getTemplateDataHolder().data
    }
}