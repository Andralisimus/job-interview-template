package com.andrejskijonoks.job_interview_template.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.andrejskijonoks.job_interview_template.databinding.FragmentMainBinding
import com.andrejskijonoks.job_interview_template.models.TemplateData

class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

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
        binding.button.setOnClickListener {
            navigateToDetails()
        }
    }

    private fun navigateToDetails() {
        navController = Navigation.findNavController(binding.root)
        val data = TemplateData(title = "title", subTitle = "subTitle")
        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(data = data)
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}