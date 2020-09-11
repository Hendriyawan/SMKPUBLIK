package com.hicoding.smkpubmak.views.activity.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hicoding.smkpubmak.R
import com.hicoding.smkpubmak.views.activity.intro.IntroActivity
import com.hicoding.smkpubmak.views.utils.AppPreferences
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val responseSaveUser = AppPreferences.getUser(this)
            if (responseSaveUser != null) {
                startActivity<com.hicoding.smkpubmak.views.activity.main.MainActivity>()
                finish()
            } else {
                startActivity<IntroActivity>()
            }
        }, 3000)
    }
}