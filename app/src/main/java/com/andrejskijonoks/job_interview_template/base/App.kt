package com.andrejskijonoks.job_interview_template.base

import android.app.Application
import com.andrejskijonoks.job_interview_template.api.RetrofitProvider
import com.andrejskijonoks.job_interview_template.api.OpticsPlanetRepository
import com.andrejskijonoks.job_interview_template.viewModels.CategoriesViewModel
import com.andrejskijonoks.job_interview_template.viewModels.CategoryViewModel
import com.andrejskijonoks.job_interview_template.viewModels.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@App)
            loadKoinModules(
                listOf(
                    getDataModule(),
                    getDomainModule(),
                    getViewModelModule()
                )
            )
        }
    }

    private fun getDataModule() = module {
        single { RetrofitProvider.templateService }
    }

    private fun getDomainModule() = module {
        single { OpticsPlanetRepository() }
    }

    private fun getViewModelModule() = module {
        viewModel { CategoriesViewModel(get()) }
        viewModel { CategoryViewModel(get()) }
        viewModel { ProductViewModel(get()) }
    }
}