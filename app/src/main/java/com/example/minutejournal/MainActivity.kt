package com.example.minutejournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.minutejournal.ui.main.EntriesDTO

class MainActivity : AppCompatActivity() {
    var entries: ArrayList<EntriesDTO> = ArrayList<EntriesDTO>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnNewEntry : ImageButton = findViewById(R.id.btnNew)
        btnNewEntry.setOnClickListener{
            val intent = Intent(this, CreateEntryActivity:: class.java)
            startActivity(intent)
        }
    }
    fun addEntry(entry:EntriesDTO){
        entries.add(entry)
    }
}
