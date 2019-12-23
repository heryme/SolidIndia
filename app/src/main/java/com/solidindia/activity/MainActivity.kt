package com.solidindia.activity

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.frament.HomeFragment
import com.frament.ProductFragment
import com.frament.ProfileFragment
import com.frament.WhatsAppFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solidindia.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import android.R.attr.fragment
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R.attr.name
import android.R.attr.fragment

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R.attr.name
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG: String = javaClass.simpleName
    lateinit var llBackMain: LinearLayoutCompat
    lateinit var ivbarToolbar: ImageView

    companion object {
        var ivMoreData: ImageView? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setListner()
        loadFragment(HomeFragment(), false)
    }

    fun setListner() {
        llBackMain.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            llBackMain -> {
                fragmentHandling()
            }
        }
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
        llBackMain = findViewById(R.id.llBack)
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
        val frag = supportFragmentManager.findFragmentByTag(
            supportFragmentManager.fragments.get(supportFragmentManager.backStackEntryCount - 1).tag
        )
        Log.e(TAG, "Frament name-->" + frag)
        if (frag is HomeFragment) {
            llBackMain.visibility = View.VISIBLE
            ivbarToolbar.visibility = View.GONE
        } else if (frag is ProductFragment) {
            llBackMain.visibility = View.GONE
            ivbarToolbar.visibility = View.VISIBLE
        } else {
            ivbarToolbar.visibility = View.VISIBLE
            llBackMain.visibility = View.GONE
        }
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
                        loadFragment(ProfileFragment(), true)
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
        val btn_dialog_app_no = dialog.findViewById(R.id.btn_dialog_app_no) as TextView
        val btn_dialog_app_yes = dialog.findViewById(R.id.btn_dialog_app_yes) as TextView
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
