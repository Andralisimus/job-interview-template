package com.andrejskijonoks.job_interview_template.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.andrejskijonoks.job_interview_template.databinding.ViewActionBarBinding

class ActionBar : LinearLayout {

    private val binding: ViewActionBarBinding =
        ViewActionBarBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    fun init(
        title: String,
        onBackClick: (() -> Unit)? = null,
        onHomeClick: (() -> Unit)? = null
    ) {
        binding.title.text = title

        if (onBackClick == null) binding.backImage.visibility = View.GONE
        else binding.backImage.setOnClickListener { onBackClick.invoke() }

        if (onHomeClick == null) binding.homeImage.visibility = View.GONE
        else binding.homeImage.setOnClickListener { onHomeClick.invoke() }
    }
}