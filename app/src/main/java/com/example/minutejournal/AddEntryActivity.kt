package com.example.minutejournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class AddEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        val button = findViewById<TextView>(R.id.doneBtn)
        button.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, EntriesActivity::class.java)
            startActivity(intent);

        }

        val button2 = findViewById<TextView>(R.id.cancelBtn)
        button2.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, EntriesActivity::class.java)
            startActivity(intent);

        }
    }
}