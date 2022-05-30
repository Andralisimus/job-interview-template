package com.andrejskijonoks.job_interview_template.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrejskijonoks.job_interview_template.api.TemplateRepository
import kotlinx.coroutines.launch

class TemplateViewModel(private var repository: TemplateRepository) : ViewModel() {

    fun getCurrencies() {
        viewModelScope.launch {
            val response = repository.getCurrencies()
        }
    }

}