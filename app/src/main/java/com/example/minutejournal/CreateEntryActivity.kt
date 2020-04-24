package com.example.minutejournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.minutejournal.ui.main.EntriesDTO
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_create_entry.*
import java.time.LocalDate
import java.util.*

class CreateEntryActivity : AppCompatActivity() {
    private val AUTH_REQUEST_CODE = 3453
    var user : FirebaseUser? = null
    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var entry: EntriesDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)



        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()


        //When cancel button is clicked, prompt for confirmation
        val btnCancelEntry : ImageButton = findViewById(R.id.btnCancel)
        btnCancelEntry.setOnClickListener{
            val builder = AlertDialog.Builder(this@CreateEntryActivity)
            builder.setTitle("Cancel Entry?")
            builder.setMessage("Are you sure you want to cancel this entry? All progress will be lost!")
            builder.setPositiveButton("Yes"){dialog, which ->
                Toast.makeText(applicationContext, "Entry cancelled", Toast.LENGTH_SHORT).show()
                finish()
            }
            builder.setNegativeButton("No"){dialog, which ->}
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        val btnSaveEntry : ImageButton = findViewById(R.id.btnSave)
        btnSaveEntry.setOnClickListener{
            if(txtTitle.text.isBlank() || txtEntry.text.isBlank()) {
                Toast.makeText(applicationContext, "Entry Incomplete", Toast.LENGTH_SHORT).show()
            }
            else {
                entry = EntriesDTO(
                    txtTitle.text.toString(),
                    txtEntry.text.toString(),
                    LocalDate.now()
                )
                //TODO: Fix this auth check. Doesn't work
                if(FirebaseAuth.getInstance().currentUser == null)
                {
                   // logon()
                }
                else {
                    saveEntry(entry)
                    Toast.makeText(applicationContext, "Entry Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
    fun saveEntry(entriesDTO: EntriesDTO){
        firestore.collection("Entries")
            .document()
            .set(entry)
            .addOnSuccessListener {
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener{
                Log.d("Firebase", "Save Failed")
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            if (requestCode == AUTH_REQUEST_CODE) {
                user = FirebaseAuth.getInstance().currentUser
            }
        }
    }
    /*fun logon() {
        var providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), AUTH_REQUEST_CODE
        )
    }*/
}
