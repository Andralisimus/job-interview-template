package com.andrejskijonoks.job_interview_template.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrejskijonoks.job_interview_template.api.OpticsPlanetRepository
import com.andrejskijonoks.job_interview_template.base.GLIDE_DOMAIN
import com.andrejskijonoks.job_interview_template.base.GLIDE_FORMAT
import com.andrejskijonoks.job_interview_template.base.SMALL_IMAGE_PATH
import com.andrejskijonoks.job_interview_template.models.Product
import kotlinx.coroutines.launch

class CategoryViewModel(private var repository: OpticsPlanetRepository) : ViewModel() {

    var products = MutableLiveData<List<Product>>()
    private val gridSize = 20
    private var page = 1
    private var total : Int = 0
    private lateinit var identifier : String

    private fun getProducts() {
        viewModelScope.launch {
            val response = repository.getProducts(
                identifier = identifier,
                gridSize = gridSize,
                page = page
            )
            val newProducts = response.gridProducts["elements"] ?: listOf()
            total = response.total

            val presentationData = mapToPresentation(newProducts)
            val combinedProducts = combineProductLists(presentationData)

            products.postValue(combinedProducts)
        }
    }

    private fun combineProductLists(newProducts: List<Product>) : List<Product> {
        return if(page != 1 && products.value != null) {
            products.value?.plus(newProducts) ?: listOf()
        } else newProducts
    }

    fun loadFirstPage(identifier: String? = null) {
        identifier?.let { this.identifier = it }
        page = 1
        getProducts()
    }

    fun loadNextPage() {
        if(gridSize * page < total) {
            page++
            getProducts()
        }
    }

    private fun mapToPresentation(products: List<Product>): List<Product> {
        products.forEach {
            it.imagePath = "${GLIDE_DOMAIN}/${SMALL_IMAGE_PATH}/${it.imagePath}${GLIDE_FORMAT}"
            it.presentationPrice = "${it.price}$"
        }
        return products
    }

}