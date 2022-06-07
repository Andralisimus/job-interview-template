package com.andrejskijonoks.job_interview_template.viewModels

import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrejskijonoks.job_interview_template.api.OpticsPlanetRepository
import com.andrejskijonoks.job_interview_template.base.GLIDE_DOMAIN
import com.andrejskijonoks.job_interview_template.base.GLIDE_FORMAT
import com.andrejskijonoks.job_interview_template.base.LARGE_IMAGE_PATH
import com.andrejskijonoks.job_interview_template.models.DetailedProduct
import kotlinx.coroutines.launch

class ProductViewModel(private var repository: OpticsPlanetRepository) : ViewModel() {

    var data = MutableLiveData<DetailedProduct>()

    fun getProduct(identifier: String) {
        viewModelScope.launch {
            val response = repository.getProduct(identifier = identifier)
            val presentationData = mapToPresentation(response)
            data.postValue(presentationData)
        }
    }

    private fun mapToPresentation(product: DetailedProduct): DetailedProduct {

        product.detailsImagePath =
            "${GLIDE_DOMAIN}/${LARGE_IMAGE_PATH}/${product.detailsImagePath}${GLIDE_FORMAT}"
        product.presentationPrice = "${product.minSalePrice}$"
        product.presentationDescription =
            HtmlCompat.fromHtml(product.description, HtmlCompat.FROM_HTML_MODE_LEGACY)

        return product
    }

}