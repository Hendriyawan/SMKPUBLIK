package com.hicoding.smkpubmak.views.activity.base

import android.app.Dialog
import android.view.Gravity
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hicoding.smkpubmak.R

@Suppress("DEPRECATION")
open class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialog: Dialog

    protected fun initProgressDialog() {
        progressDialog = Dialog(this)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.layout_dialog_progress)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val window = progressDialog.window
        window?.setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window?.setGravity(Gravity.CENTER)
        progressDialog.setCancelable(false)
    }

    protected fun showProgress() {
        if(progressDialog.isShowing) return
        progressDialog.show()
    }

    protected fun hideProgress() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }

    protected fun loadFragment(fragment : Fragment, container : Int){
        supportFragmentManager.beginTransaction()
            .replace(container, fragment, fragment::class.java.simpleName).commit()
    }
}