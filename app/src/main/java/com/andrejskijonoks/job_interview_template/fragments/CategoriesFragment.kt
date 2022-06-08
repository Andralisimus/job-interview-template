package com.andrejskijonoks.job_interview_template.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.andrejskijonoks.job_interview_template.R
import com.andrejskijonoks.job_interview_template.base.BaseFragment
import com.andrejskijonoks.job_interview_template.databinding.FragmentCategoriesBinding
import com.andrejskijonoks.job_interview_template.epoxy.CategoriesController
import com.andrejskijonoks.job_interview_template.models.Category
import com.andrejskijonoks.job_interview_template.viewModels.CategoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesFragment : BaseFragment() {

    private var _binding : FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel: CategoriesViewModel by viewModel()
    private lateinit var epoxyController: CategoriesController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initializeActionBar()
        initializeController()

        if(viewModel.data.value.isNullOrEmpty()) {
            viewModel.getCategories()
            epoxyController.loading = true
        } else epoxyController.data = viewModel.data.value ?: listOf()
    }

    private fun initializeController() {
        navController = Navigation.findNavController(binding.root)
        epoxyController = CategoriesController { navigateToDetails(it) }
        binding.epoxyView.setController(epoxyController)
    }

    private fun initializeActionBar() {
        binding.actionBar.init(title = getString(R.string.brand_name))
    }

    private fun observe() {
        viewModel.data.observe(viewLifecycleOwner) {
            epoxyController.data = it
        }
    }

    private fun navigateToDetails(category: Category) {
        if(isActionAllowed()) {
            val action = CategoriesFragmentDirections.actionMainFragmentToDetailsFragment(category = category)
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}