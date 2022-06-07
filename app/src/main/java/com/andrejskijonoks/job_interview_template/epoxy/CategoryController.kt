package com.andrejskijonoks.job_interview_template.epoxy

import com.airbnb.epoxy.EpoxyController
import com.andrejskijonoks.job_interview_template.base.LOADER_MODEL_ID
import com.andrejskijonoks.job_interview_template.models.Product

class CategoryController(
    private val onClicked: (Product) -> Unit
) : EpoxyController() {

    var data: List<Product> = listOf()
        set(value) {
            field = value
            loading = false
            requestModelBuild()
        }

    var loading: Boolean = false
        set(value) {
            field = value
            if (field) requestModelBuild()
        }

    override fun buildModels() {
        if (loading) {
            LoaderModel().id(LOADER_MODEL_ID).addTo(this)
            return
        }

        if (data.isNotEmpty()) {
            data.forEach {
                ProductModel(product = it, onClicked = onClicked).id(it.name).addTo(this)
            }
        }
    }
}