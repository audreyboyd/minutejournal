package com.example.minutejournal.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minutejournal.MainActivity
import com.example.minutejournal.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    private fun register () {
        var clickRIntent = Intent(this, RegisterActivity :: class.java)
        startActivity(clickRIntent)
    }
    private fun login () {
        val emailTxt = findViewById<View>(R.id.etEmail) as EditText
        val passwordTxt = findViewById<View>(R.id.etPassword) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    var clickIntent = Intent(this, MainActivity :: class.java)
                    startActivity(clickIntent)
                    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            })
        }
        else{
            Toast.makeText(this, "Please fill in your login credentials. " +
                    "If you need to register, click the button below.", Toast.LENGTH_LONG).show()

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBttn : Button = findViewById(R.id.bttnLogin)
        val regBttn : Button = findViewById(R.id.bttnRegister)

        loginBttn.setOnClickListener{
            login()
        }
        regBttn.setOnClickListener{
            register()
        }
    }
}