package com.andrejskijonoks.job_interview_template.epoxy

import com.airbnb.epoxy.EpoxyController
import com.andrejskijonoks.job_interview_template.models.TemplateData

class TemplateController(
    private val onClicked: (TemplateData) -> Unit
) : EpoxyController() {

    var data: List<TemplateData> = listOf()
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
        if(data.isNotEmpty()) {
            data.forEach {
                TemplateModel(data = it, onClicked = onClicked).id(it.subTitle).addTo(this)
            }
        }
    }

}