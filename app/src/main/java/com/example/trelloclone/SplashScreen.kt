package com.example.trelloclone

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.trelloclone.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {
    private var binding: ActivitySplashScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }, 2500)
    }
}































