package com.utils

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.LocaleList
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import java.util.*

private var toast: Toast? = null

@SuppressLint("ShowToast")
fun Toast(msg: Any?, isShort: Boolean = true, app: Context) {
    msg?.let {
        if (toast == null) {
            toast = Toast.makeText(app, msg.toString(), Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(msg.toString())
        }
        toast!!.duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
        toast!!.show()
    }
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun getProgressDialog(context: Context): AppCompatDialog {

    val isCalncelable = false
    val myCustomProgressDialog = com.utils.ProgressDialog(context)
    myCustomProgressDialog.setCancelable(isCalncelable)
    myCustomProgressDialog.show()
    return myCustomProgressDialog

}

fun dismissDialog(context: Context, mProgressDialog: AppCompatDialog) {
    try {
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    } catch (e: Exception) {
    }

}

fun isNetWork(context: Context): Boolean {
    val flag: Boolean = isNetworkAvailable(context)
    return flag
}

fun isNetworkAvailable(context: Context): Boolean {

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null
}

fun setLocale(context: Context,localeString: String) {
    val res = context.resources
    val conf = res.configuration
    val locale = Locale(localeString)
    Locale.setDefault(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        conf.setLocale(locale)
        context?.getApplicationContext()?.createConfigurationContext(conf)
    }

    val dm = res.displayMetrics
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        conf.locales = LocaleList(locale)
    } else {
        conf.locale = locale
    }
    res.updateConfiguration(conf, dm)
}

fun getLanguageType(context: Context,type: String){
    Log.e("TAG","Selecetd Language Type---$type")
    if(type.equals("en")) {
        setLocale(context,"en")
    }else if (type.equals("ar")) {
        setLocale(context,"ar")
    }else if (type.equals("sp")) {
        setLocale(context,"es")
    }else if (type.equals("ch")) {
        setLocale(context,"zh")
    }else if (type.equals("fr")) {
        setLocale(context,"fr")
    }else if (type.equals("tm")) {
        setLocale(context,"ta")
    }else if (type.equals("te")) {
        setLocale(context,"te")
    }
}

fun loadImage(imagePath: String, context: Context, targetImageView: ImageView,errorImage: Int) {
    Glide.with(context)
        .load(imagePath)
        /*.diskCacheStrategy(DiskCacheStrategy.SOURCE)
        .skipMemoryCache(false)*/
        .listener(object : RequestListener<String, GlideDrawable> {
            override fun onResourceReady(resource: GlideDrawable?, model: String?, target: com.bumptech.glide.request.target.Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                targetImageView.scaleType = ImageView.ScaleType.FIT_XY
                return false
            }

            override fun onException(e: java.lang.Exception?, model: String?, target: com.bumptech.glide.request.target.Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                targetImageView.scaleType = ImageView.ScaleType.CENTER
                targetImageView.setImageResource(errorImage)
                return false
            }

        })
        .error(errorImage).into(targetImageView)
}