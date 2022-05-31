package com.andrejskijonoks.job_interview_template.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.andrejskijonoks.job_interview_template.databinding.FragmentMainBinding
import com.andrejskijonoks.job_interview_template.epoxy.TemplateController
import com.andrejskijonoks.job_interview_template.models.TemplateData
import com.andrejskijonoks.job_interview_template.viewModels.TemplateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel: TemplateViewModel by viewModel()
    private lateinit var epoxyController: TemplateController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()

        navController = Navigation.findNavController(binding.root)
        epoxyController = TemplateController { navigateToDetails(it) }
        binding.epoxyView.setController(epoxyController)

        viewModel.getCurrencies()
    }

    private fun observe() {
        viewModel.data.observe(viewLifecycleOwner) {
            epoxyController.data = it
        }
    }

    private fun navigateToDetails(data: TemplateData) {
        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(data = data)
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}