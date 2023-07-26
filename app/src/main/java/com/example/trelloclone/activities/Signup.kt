package com.example.trelloclone.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trelloclone.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class signup : BaseActivity() {
    private var binding: ActivitySignupBinding? = null
    private lateinit var auth: FirebaseAuth
    private var sharedPreference: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
        sharedPreference = getSharedPreferences("Trello", Context.MODE_PRIVATE)

        binding?.backbutton?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding?.tvLogin?.setOnClickListener {
            startActivity(Intent(this, signInActivity::class.java))
            finish()
        }
        binding?.signUpButton?.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {
        val name: String = binding?.nameField?.text.toString().trim { it <= ' ' }
        val email: String = binding?.emailField?.text.toString().trim { it <= ' ' }
        val password: String = binding?.passwordField?.text.toString().trim { it <= ' ' }
        if (validateForm(name, email, password)) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success")
                        val user = auth.currentUser
                        binding?.nameField?.text?.clear()
                        binding?.emailField?.text?.clear()
                        binding?.passwordField?.text?.clear()
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
                this@signup,
                "Now we can register a new user.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter name.")
                false
            }

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

//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            Log.e("user Found", "user")
//        } else {
//            Log.e("No user Found", "user")
//        }
//    }
}
