package com.andrejskijonoks.job_interview_template.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.andrejskijonoks.job_interview_template.base.BaseFragment
import com.andrejskijonoks.job_interview_template.databinding.FragmentCategoryBinding
import com.andrejskijonoks.job_interview_template.epoxy.CategoryController
import com.andrejskijonoks.job_interview_template.models.Product
import com.andrejskijonoks.job_interview_template.viewModels.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : BaseFragment() {

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
        initializeRefreshLayout()

        if(viewModel.products.value.isNullOrEmpty()) {
            viewModel.loadFirstPage(identifier = args.category.url)
            epoxyController.loading = true
        } else epoxyController.data = viewModel.products.value ?: listOf()
    }

    private fun initializeRefreshLayout() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.loadFirstPage()
        }
    }

    private fun initializeController() {
        navController = Navigation.findNavController(binding.root)
        epoxyController = CategoryController { navigateToDetails(it) }
        binding.epoxyView.setController(epoxyController)
        binding.epoxyView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy > 0 && isPaginationAllowed()) {
                    viewModel.loadNextPage()
                }
            }
        })
    }

    private fun initializeActionBar() {
        binding.actionBar.init(
            title = args.category.name,
            onBackClick = { if(isActionAllowed()) onBackPressed() }
        )
    }

    private fun observe() {
        viewModel.products.observe(viewLifecycleOwner) {
            epoxyController.data = it
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun navigateToDetails(product: Product) {
        if(isActionAllowed()) {
            val action = CategoryFragmentDirections.actionDetailsFragmentToProductFragment(product = product)
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}