package com.solidindia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import com.app.AppController
import com.solidindia.R
import com.utils.Toast
import com.utils.loadImage
import kotlinx.android.synthetic.main.activity_show_full_imae.*
import kotlinx.android.synthetic.main.toolbar.*


class ShowFullImageActivity : AppCompatActivity() {
    lateinit var appcontroller: AppController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_full_imae)
        appcontroller = application as AppController
        val extras = intent.extras
        if (extras != null) {
            var data = extras.getString("data")
            loadImage(
                data,
                appcontroller.getAppContext()!!, ivFullImage,
                R.mipmap.ic_launcher
            )
        }

    }
}
