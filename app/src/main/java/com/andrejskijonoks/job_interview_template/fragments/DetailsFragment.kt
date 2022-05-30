package com.andrejskijonoks.job_interview_template.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.andrejskijonoks.job_interview_template.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val args: DetailsFragmentArgs by navArgs()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = "${args.data.title} ${args.data.subTitle}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}