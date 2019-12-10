package com.solidindia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.frament.HomeFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import android.widget.Toast
import com.frament.ProductFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.Button
import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.widget.TextViewCompat
import com.frament.WhatsAppFragment
import com.solidindia.R
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

    private var searchView: SearchView? = null
    lateinit var ivbarToolbar:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        loadFragment(HomeFragment(), false)
    }

    /**
     * Load Fragment
     * @param fragment
     * @param isMenuItem
     */
    fun loadFragment(fragment: Fragment, isMenuItem: Boolean) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_container, fragment, fragment.javaClass.getSimpleName())
        if (isMenuItem) {
            val fm = supportFragmentManager
            for (i in 0 until fm.backStackEntryCount) {
                //Clear Fragment Back Stack
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
        fragmentTransaction.addToBackStack(fragment.javaClass.getSimpleName())
        fragmentTransaction.commit()

    }

    private fun initView() {
        ivbarToolbar = findViewById(R.id.ivbarToolbar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setTitle(null);

    }

    override fun onBackPressed() {
        //super.onBackPressed();
        if (supportFragmentManager.backStackEntryCount == 1) {
            showExitDialog()
        } else {
            fragmentHandling()
        }
    }

     fun fragmentHandling() {
        //Fragment Remove From The Stack
        supportFragmentManager.popBackStack()
    }

    private val mOnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val fragment: Fragment
                when (item.getItemId()) {
                    R.id.navigation_home -> {
                        loadFragment(HomeFragment(), true)
                        return true
                    }
                    R.id.navigation_product -> {
                        loadFragment(ProductFragment(), true)
                        return true
                    }
                    R.id.navigation_profile -> {
                        return true
                    }
                    R.id.navigation_whats_app -> {
                        loadFragment(WhatsAppFragment(), true)
                        return true
                    }
                }
                return false
            }
        }




    /**
     * Show Exit Dialog
     */
    private fun showExitDialog() {

        val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_application_exit)
        dialog.setCancelable(false)
        val btn_dialog_app_no = dialog.findViewById(R.id.btn_dialog_app_no) as Button
        val btn_dialog_app_yes = dialog.findViewById(R.id.btn_dialog_app_yes) as Button
        dialog.show()
        btn_dialog_app_no.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                dialog.dismiss()
            }
        })
        btn_dialog_app_yes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                finish()
                System.exit(0)
            }
        })
    }

}
