package com.andrejskijonoks.job_interview_template.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TemplateData(
    @SerializedName("name")
    val title: String,
    @SerializedName("id")
    val subTitle: String
) : Parcelable

data class TemplateDataHolder(
    val data: List<TemplateData>
)