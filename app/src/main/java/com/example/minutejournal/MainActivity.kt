package com.example.minutejournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val button = findViewById<ImageView>(R.id.numberOK)
        button.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, EntriesActivity::class.java)
            startActivity(intent);
        }
    }
}
