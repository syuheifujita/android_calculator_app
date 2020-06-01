package com.example.android_app_calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onDigit(view: View) {
        tv_input_number.append((view as Button).text)
    }

    fun onClear(view: View) {
        tv_input_number.text = ""
    }
}
