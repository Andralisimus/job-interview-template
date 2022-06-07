package com.andrejskijonoks.job_interview_template.epoxy

import com.andrejskijonoks.job_interview_template.R
import com.andrejskijonoks.job_interview_template.base.ViewBindingKotlinModel
import com.andrejskijonoks.job_interview_template.databinding.ProductItemBinding
import com.andrejskijonoks.job_interview_template.models.Product
import com.bumptech.glide.Glide

class ProductModel(
    private val product: Product,
    private val onClicked: (Product) -> Unit
) : ViewBindingKotlinModel<ProductItemBinding>(R.layout.product_item) {

    override fun ProductItemBinding.bind() {

        title.text = product.name
        price.text = product.presentationPrice

        Glide.with(root)
            .load(product.imagePath)
            .into(imageView)

        root.setOnClickListener {
            onClicked.invoke(product)
        }
    }
}