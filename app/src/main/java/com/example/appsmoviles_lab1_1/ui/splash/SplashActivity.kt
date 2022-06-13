package com.example.appsmoviles_lab1_1.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appsmoviles_lab1_1.databinding.ActivityMainBinding
import com.example.appsmoviles_lab1_1.databinding.ActivitySplashBinding
import com.example.appsmoviles_lab1_1.ui.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

private lateinit var splashBinding: ActivitySplashBinding

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)

        //Temporaizador para retornar al ui.main Activity
        val timer = Timer()
        timer.schedule(
            timerTask{
            goToMainActivity()
        }, 1000
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}