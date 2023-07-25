package com.example.trelloclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trelloclone.databinding.ActivitySignupBinding

class signup : AppCompatActivity() {
    private var binding: ActivitySignupBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.backbutton?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}