package com.example.minutejournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.minutejournal.ui.main.EntriesDTO
import kotlinx.android.synthetic.main.activity_create_entry.*
import java.time.LocalDate
import java.util.*

class CreateEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        lateinit var entry: EntriesDTO

        //When cancel button is clicked, prompt for confirmation
        val btnCancelEntry : ImageButton = findViewById(R.id.btnCancel)
        btnCancelEntry.setOnClickListener{
            val builder = AlertDialog.Builder(this@CreateEntryActivity)
            builder.setTitle("Cancel Entry?")
            builder.setMessage("Are you sure you want to cancel this entry? All progress will be lost!")
            builder.setPositiveButton("Yes"){dialog, which ->
                Toast.makeText(applicationContext, "Entry cancelled", Toast.LENGTH_SHORT).show()
                finish();
            }
            builder.setNegativeButton("No"){dialog, which ->}
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        val btnSaveEntry : ImageButton = findViewById(R.id.btnSave)
        btnSaveEntry.setOnClickListener{
            entry = EntriesDTO(txtTitle.text.toString(), txtEntry.text.toString(), LocalDate.now().toString())

        }
    }
}
