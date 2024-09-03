package com.ismailmesutmujde.kotlinrunnable

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ismailmesutmujde.kotlinrunnable.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var bindingMainActivity: ActivityMainBinding
    var number = 0
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMainActivity.root
        setContentView(view)

    }

    fun start(view: View) {

        number = 0
        runnable = object : Runnable {
            override fun run() {
                number = number + 1
                bindingMainActivity.textView.text = "Time : ${number}"
                //handler.postDelayed(this,1000) -> this, runnable' a referans verir
                handler.postDelayed(runnable,1000)
            }

        }
        handler.post(runnable)
        bindingMainActivity.button.isEnabled = false
    }

    fun stop(view: View) {
        bindingMainActivity.button.isEnabled = true
        number = 0
        bindingMainActivity.textView.text = "Time : 0"
        handler.removeCallbacks(runnable)
    }
}