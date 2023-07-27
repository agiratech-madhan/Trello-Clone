package com.example.trelloclone.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.trelloclone.activities.IntroActivity
import com.example.trelloclone.databinding.ActivitySplashScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SplashScreen : AppCompatActivity() {
    private var binding: ActivitySplashScreenBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        var typeFace: Typeface = Typeface.createFromAsset(assets, "Poppins-Medium.ttf")
        binding?.tvAppName?.typeface = typeFace
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val insetsController = window.insetsController
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                insetsController.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,

                )
        }

//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this, IntroActivity::class.java))
//            finish()
//        }, 2500)

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Log.e("No user Found", "user")
            startActivity(Intent(this, IntroActivity::class.java))
            finish()

        }
    }
}































