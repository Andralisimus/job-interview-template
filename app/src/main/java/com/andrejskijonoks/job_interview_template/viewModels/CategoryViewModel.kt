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

    var data = MutableLiveData<List<Product>>()

    fun getProducts(identifier: String) {
        viewModelScope.launch {
            val response = repository.getProducts(identifier = identifier)
            val presentationData = mapToPresentation(response)
            data.postValue(presentationData)
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