package com.andrejskijonoks.job_interview_template.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.andrejskijonoks.job_interview_template.databinding.FragmentCategoryBinding
import com.andrejskijonoks.job_interview_template.epoxy.CategoriesController
import com.andrejskijonoks.job_interview_template.epoxy.CategoryController
import com.andrejskijonoks.job_interview_template.models.Category
import com.andrejskijonoks.job_interview_template.models.Product
import com.andrejskijonoks.job_interview_template.viewModels.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment() {

    private var _binding : FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val args: CategoryFragmentArgs by navArgs()
    private val viewModel: CategoryViewModel by viewModel()
    private lateinit var epoxyController: CategoryController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initializeActionBar()
        initializeController()

        if(viewModel.data.value.isNullOrEmpty()) {
            viewModel.getProducts(identifier = args.category.url)
            epoxyController.loading = true
        } else epoxyController.data = viewModel.data.value ?: listOf()
    }

    private fun initializeController() {
        navController = Navigation.findNavController(binding.root)
        epoxyController = CategoryController { navigateToDetails(it) }
        binding.epoxyView.setController(epoxyController)
    }

    private fun initializeActionBar() {
        binding.actionBar.init(title = args.category.name) {
            requireActivity().onBackPressed()
        }
    }

    private fun observe() {
        viewModel.data.observe(viewLifecycleOwner) {
            epoxyController.data = it
        }
    }

    private fun navigateToDetails(product: Product) {
        val action = CategoryFragmentDirections.actionDetailsFragmentToProductFragment(product = product)
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}