package com.example.trelloclone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.trelloclone.Home
import com.example.trelloclone.R
import com.example.trelloclone.databinding.ActivityMainBinding
import com.example.trelloclone.profile
import com.example.trelloclone.shop
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
        binding?.logout?.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }

        binding?.moreIcon?.setOnClickListener {


            showPopupMenu(it)

        }
        replaceFragment(Home())


        binding?.bottomNavigationView?.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(profile())
                R.id.shop -> replaceFragment(shop())

                else -> {


                }

            }

            true

        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_popup)

        // Set a listener to handle clicks on menu items
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_1 -> {
                    Log.d("Selected", "first")
                    // Handle click on Menu Item 1
                    true
                }

                R.id.menu_item_2 -> {
                    Log.d("Selected", "second")

                    // Handle click on Menu Item 2
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()


    }
}