package com.andrejskijonoks.job_interview_template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.andrejskijonoks.job_interview_template.base.MIN_PAUSE_BETWEEN_ACTIONS
import com.andrejskijonoks.job_interview_template.base.MIN_PAUSE_BETWEEN_PAGINATION
import com.andrejskijonoks.job_interview_template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastActionTime: Long = 0
    private var lastPaginationTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //Prevents user from double click
    fun isActionAllowed(): Boolean {
        return if (SystemClock.elapsedRealtime() - lastActionTime >= MIN_PAUSE_BETWEEN_ACTIONS) {
            lastActionTime = SystemClock.elapsedRealtime()
            true
        } else false
    }

    //Prevents user from pagination DDOS
    fun isPaginationAllowed(): Boolean {
        return if (SystemClock.elapsedRealtime() - lastPaginationTime >= MIN_PAUSE_BETWEEN_PAGINATION) {
            lastPaginationTime = SystemClock.elapsedRealtime()
            true
        } else false
    }
}