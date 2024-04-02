package com.example.nbktask.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.nbktask.R
import com.example.nbktask.common.BaseBindingActivity
import com.example.nbktask.compose.ComposeMainActivity
import com.example.nbktask.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : BaseBindingActivity<ActivitySplashBinding>(R.layout.activity_splash)  {
    val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isUserLoggedIn()
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                val intent = Intent(this, ComposeMainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }
}