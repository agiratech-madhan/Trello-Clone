package com.example.trelloclone.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trelloclone.databinding.ActivitySignInBinding
import com.example.trelloclone.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class signInActivity : BaseActivity() {
    private var binding: ActivitySignInBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignInBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        auth = Firebase.auth
        binding?.signInButton?.setOnClickListener {
            signInUser()
        }
        binding?.Registeruser?.setOnClickListener {
            startActivity(Intent(this, signup::class.java))
        }

    }

    private fun signInUser() {
        val email: String = binding?.emailFieldSignIn?.text.toString().trim { it <= ' ' }
        val password: String = binding?.passwordFieldSignIn?.text.toString().trim { it <= ' ' }
        if (validateForm(email, password)) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success")
                        val user = auth.currentUser

                        binding?.emailFieldSignIn?.text?.clear()
                        binding?.passwordFieldSignIn?.text?.clear()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
//                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
//                        updateUI(null)
                    }
                }
            Toast.makeText(
                this@signInActivity,
                "Now we can register a new user.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun validateForm(email: String, password: String): Boolean {
        return when {


            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }

            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }

            else -> {
                true
            }
        }
    }

}