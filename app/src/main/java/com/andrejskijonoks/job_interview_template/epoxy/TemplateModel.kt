package com.andrejskijonoks.job_interview_template.epoxy

import com.andrejskijonoks.job_interview_template.R
import com.andrejskijonoks.job_interview_template.base.ViewBindingKotlinModel
import com.andrejskijonoks.job_interview_template.databinding.ItemBinding
import com.andrejskijonoks.job_interview_template.models.TemplateData

class TemplateModel(
    private val data: TemplateData,
    private val onClicked: (TemplateData) -> Unit
) : ViewBindingKotlinModel<ItemBinding>(R.layout.item) {

    override fun ItemBinding.bind() {
        title.text = data.title
        subTitle.text = data.subTitle

        root.setOnClickListener {
            onClicked.invoke(data)
        }
    }
}