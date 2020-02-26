package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val btnStartListener = View.OnClickListener {
        val intent = Intent(this@MainActivity, Main2Activity::class.java)
        startActivity(intent)
    }
    private val btnSettingsListener = View.OnClickListener {
        val intent = Intent(this@MainActivity, Main3Activity::class.java)
        startActivity(intent)
    }
    private val btnExitListener = View.OnClickListener { finish() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart = findViewById<Button>(R.id.btn1)
        val btnSettings = findViewById<Button>(R.id.btn2)
        val btnExit = findViewById<Button>(R.id.btn3)
        btnStart.setOnClickListener(btnStartListener)
        btnSettings.setOnClickListener(btnSettingsListener)
        btnExit.setOnClickListener(btnExitListener)
    }
}