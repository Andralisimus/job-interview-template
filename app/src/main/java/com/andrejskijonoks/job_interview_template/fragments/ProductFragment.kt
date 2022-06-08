package com.andrejskijonoks.job_interview_template.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.andrejskijonoks.job_interview_template.R
import com.andrejskijonoks.job_interview_template.base.BaseFragment
import com.andrejskijonoks.job_interview_template.databinding.FragmentProductBinding
import com.andrejskijonoks.job_interview_template.models.DetailedProduct
import com.andrejskijonoks.job_interview_template.viewModels.ProductViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val args: ProductFragmentArgs by navArgs()
    private val viewModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        navController = Navigation.findNavController(binding.root)
        initializeActionBar()

        viewModel.getProduct(identifier = args.product.url)
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun initializeActionBar() {
        binding.actionBar.init(
            title = args.product.name,
            onBackClick = { if(isActionAllowed()) onBackPressed() },
            onHomeClick = { navController.popBackStack(R.id.categoriesFragment, false) }
        )
    }

    private fun observe() {
        viewModel.data.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            onSuccessfulLoad(product = it)
        }
    }

    private fun onSuccessfulLoad(product: DetailedProduct) {
        Glide.with(binding.root)
            .load(product.detailsImagePath)
            .into(binding.imageView)
        binding.price.text = product.presentationPrice
        binding.name.text = product.shortName
        binding.details.text = product.presentationDescription
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}