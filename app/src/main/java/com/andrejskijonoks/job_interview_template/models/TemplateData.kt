package com.andrejskijonoks.job_interview_template.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TemplateData(
    val title: String,
    val subTitle: String
) : Parcelable