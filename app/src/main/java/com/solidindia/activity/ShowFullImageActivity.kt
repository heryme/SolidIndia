package com.solidindia.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.AppController
import com.solidindia.R
import com.utils.loadImage
import kotlinx.android.synthetic.main.activity_show_full_imae.*


class ShowFullImageActivity : AppCompatActivity() {
    lateinit var appcontroller: AppController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_full_imae)
        appcontroller = application as AppController
        val extras = intent.extras
        if (extras != null) {
            if (extras.getString("data").startsWith("http://")) {
                var data = extras.getString("data")
                loadImage(
                    data,
                    appcontroller.getAppContext()!!, ivFullImage,
                    R.mipmap.ic_launcher
                )
            } else {
                ivFullImage.setImageResource(R.drawable.ic_home_banner);
            }
        }

    }
}
