package com.andrejskijonoks.job_interview_template.base

import androidx.fragment.app.Fragment
import com.andrejskijonoks.job_interview_template.MainActivity

abstract class BaseFragment : Fragment() {
    fun isActionAllowed() = (activity as MainActivity).isActionAllowed()
    fun isPaginationAllowed() = (activity as MainActivity).isPaginationAllowed()
    fun onBackPressed() = (activity as MainActivity).onBackPressed()
}