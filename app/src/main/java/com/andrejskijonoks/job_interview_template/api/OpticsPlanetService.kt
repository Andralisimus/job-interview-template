package com.andrejskijonoks.job_interview_template.api

import com.andrejskijonoks.job_interview_template.models.Category
import com.andrejskijonoks.job_interview_template.models.DetailedProduct
import com.andrejskijonoks.job_interview_template.models.ProductHolder
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpticsPlanetService {

    @GET("api/0.2/categories")
    suspend fun getCategories(): List<Category>

    @GET("iv-api/0.3/catalog/{identifier}/products")
    suspend fun getProducts(
        @Path("identifier") identifier: String,
        @Query("_iv_include") ivInclude: String = "gridProducts"
    ): ProductHolder

    @GET("api/0.3/products/{identifier}")
    suspend fun getProduct(
        @Path("identifier") identifier: String
    ): DetailedProduct

}