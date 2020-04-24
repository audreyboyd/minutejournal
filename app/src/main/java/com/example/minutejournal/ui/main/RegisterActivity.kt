package com.example.minutejournal.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.minutejournal.R
import com.google.firebase.auth.FirebaseAuth
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val regBttn = findViewById<View>(R.id.bttnRRegister) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        regBttn.setOnClickListener(View.OnClickListener {
            view -> register()
        })
    }

    private fun register() {
        val emailTxt = findViewById<View>(R.id.etREmail) as EditText
        val passwordTxt = findViewById<View>(R.id.etRPassword) as EditText
        val nameTxt = findViewById<View>(R.id.etRName) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var name = nameTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Name").setValue(name)
                    Toast.makeText(this, "Successfully signed in!", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            })
        }
        else {
            Toast.makeText(this, "Please enter credentials into the fields provided.", Toast.LENGTH_LONG).show()
        }
    }
}