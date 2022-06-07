package com.andrejskijonoks.job_interview_template.api

import com.andrejskijonoks.job_interview_template.models.Category
import com.andrejskijonoks.job_interview_template.models.DetailedProduct
import com.andrejskijonoks.job_interview_template.models.Product
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OpticsPlanetRepository : KoinComponent {
    private val service: OpticsPlanetService by inject()

    suspend fun getCategories(): List<Category> {
        return service.getCategories()
    }

    suspend fun getProducts(identifier: String): List<Product> {
        return service.getProducts(identifier).gridProducts["elements"] ?: listOf()
    }

    suspend fun getProduct(identifier: String): DetailedProduct {
        return service.getProduct(identifier)
    }
}