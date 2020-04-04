package com.example.minutejournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class EntriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entries)

        val button = findViewById<ImageView>(R.id.addEntry)
        button.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, AddEntryActivity::class.java)
            startActivity(intent);
        }
    }
}