package com.andrejskijonoks.job_interview_template.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrejskijonoks.job_interview_template.api.OpticsPlanetRepository
import com.andrejskijonoks.job_interview_template.models.Category
import kotlinx.coroutines.launch

class CategoriesViewModel(private var repository: OpticsPlanetRepository) : ViewModel() {

    var data = MutableLiveData<List<Category>>()

    fun getCategories() {
        viewModelScope.launch {
            val response = repository.getCategories()
            data.postValue(response)
        }
    }

}