package com.andrejskijonoks.job_interview_template.api

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TemplateRepository : KoinComponent {
    private val service: TemplateService by inject()

    suspend fun getCurrencies() : Any {
        return service.getCurrencies()
    }
}