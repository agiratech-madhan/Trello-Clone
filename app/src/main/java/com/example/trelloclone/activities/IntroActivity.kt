package com.example.trelloclone.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.trelloclone.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private var binding: ActivityIntroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth


        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnSignUpIntro?.setOnClickListener {

            // Launch the sign up screen.
            startActivity(Intent(this@IntroActivity, signup::class.java))
            finish()
        }
        binding?.btnSignInIntro?.setOnClickListener {
            startActivity(Intent(this@IntroActivity, signInActivity::class.java))
            finish()

        }
    }


}