package com.example.minutejournal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.minutejournal.ui.main.EntriesDTO
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private val AUTH_REQUEST_CODE = 3453
    var user : FirebaseUser? = null
    var entries: ArrayList<EntriesDTO> = ArrayList<EntriesDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnNewEntry : ImageButton = findViewById(R.id.btnNew)
        btnNewEntry.setOnClickListener{
            val intent = Intent(this, CreateEntryActivity:: class.java)
            startActivity(intent)
        }

        val btnSignIn : ImageButton = findViewById(R.id.btnLogon)
        btnSignIn.setOnClickListener{
            this.logon()
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
    fun logon() {
        var providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), AUTH_REQUEST_CODE
        )
    }

}
