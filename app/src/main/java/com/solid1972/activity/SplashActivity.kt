package com.solid1972.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.solid1972.R

class SplashActivity : AppCompatActivity() {

    private val TAG = SplashActivity::class.java.name
    private val SPLASH_TIME_OUT = 3000.0 // TODO
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        redirect()
    }

    private fun redirect() {
        handler = Handler()
        runnable = Runnable {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)

            finish()

        }
        handler.postDelayed(runnable, SPLASH_TIME_OUT.toLong())

    }
}
