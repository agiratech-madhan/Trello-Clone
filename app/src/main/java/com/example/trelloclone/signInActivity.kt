package com.example.trelloclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trelloclone.databinding.ActivitySignInBinding

class signInActivity : AppCompatActivity() {
    private var binding: ActivitySignInBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignInBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
    }
}