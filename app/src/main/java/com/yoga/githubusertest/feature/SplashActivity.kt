package com.yoga.githubusertest.feature

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.yoga.githubusertest.R
import com.yoga.githubusertest.feature.usermain.view.UserMainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        goToMainActivity()
    }

    private fun goToMainActivity() {
        Handler().postDelayed({
            UserMainActivity.start(this)
            finish()
        },
            SPLASH_WAITING_TIME
        )
    }

    companion object {
        private const val SPLASH_WAITING_TIME = 2000L // 2 seconds.
    }
}