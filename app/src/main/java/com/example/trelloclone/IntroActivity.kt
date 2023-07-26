package com.example.trelloclone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trelloclone.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private var binding: ActivityIntroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnSignUpIntro?.setOnClickListener {

            // Launch the sign up screen.
            startActivity(Intent(this@IntroActivity, signup::class.java))
        }
        binding?.btnSignInIntro?.setOnClickListener {
            startActivity(Intent(this@IntroActivity, signInActivity::class.java))
        }
    }
}