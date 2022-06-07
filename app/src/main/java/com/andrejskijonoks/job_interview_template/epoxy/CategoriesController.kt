package com.andrejskijonoks.job_interview_template.epoxy

import com.airbnb.epoxy.EpoxyController
import com.andrejskijonoks.job_interview_template.base.LOADER_MODEL_ID
import com.andrejskijonoks.job_interview_template.models.Category

class CategoriesController(
    private val onClicked: (Category) -> Unit
) : EpoxyController() {

    var data: List<Category> = listOf()
    set(value) {
        field = value
        loading = false
        requestModelBuild()
    }

    var loading: Boolean = false
    set(value) {
        field = value
        if(field) requestModelBuild()
    }

    override fun buildModels() {
        if(loading) {
            LoaderModel().id(LOADER_MODEL_ID).addTo(this)
            return
        }

        if(data.isNotEmpty()) {
            data.forEach {
                CategoryModel(data = it, onClicked = onClicked).id(it.id).addTo(this)
            }
        }
    }
}