package com.andrejskijonoks.job_interview_template.epoxy

import com.andrejskijonoks.job_interview_template.R
import com.andrejskijonoks.job_interview_template.base.ViewBindingKotlinModel
import com.andrejskijonoks.job_interview_template.databinding.CategoryItemBinding
import com.andrejskijonoks.job_interview_template.models.Category

class CategoryModel(
    private val data: Category,
    private val onClicked: (Category) -> Unit
) : ViewBindingKotlinModel<CategoryItemBinding>(R.layout.category_item) {

    override fun CategoryItemBinding.bind() {
        title.text = data.name

        root.setOnClickListener {
            onClicked.invoke(data)
        }
    }
}