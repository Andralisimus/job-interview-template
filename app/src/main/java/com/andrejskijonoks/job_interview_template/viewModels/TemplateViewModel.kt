package com.andrejskijonoks.job_interview_template.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrejskijonoks.job_interview_template.api.TemplateRepository
import com.andrejskijonoks.job_interview_template.models.TemplateData
import kotlinx.coroutines.launch

class TemplateViewModel(private var repository: TemplateRepository) : ViewModel() {

    var data = MutableLiveData<List<TemplateData>>()

    fun getCurrencies() {
        viewModelScope.launch {
            val response = repository.getTemplateData()
            data.postValue(response)
        }
    }

}