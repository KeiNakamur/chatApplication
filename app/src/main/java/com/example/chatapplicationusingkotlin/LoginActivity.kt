package com.example.chatapplicationusingkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email_et: EditText
    private lateinit var password_et: EditText
    private lateinit var login_btn: Button
    private lateinit var signup_btn: Button
    private lateinit var actionBar: ActionBar
    private lateinit var progressBar: ProgressBar
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email_et = findViewById(R.id.email_et)
        password_et = findViewById(R.id.password_et)
        login_btn = findViewById(R.id.login_btn)
        signup_btn = findViewById(R.id.signup_btn)

        //ActionBar
        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        signup_btn.setOnClickListener(){
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        //loginボタンクリック
        login_btn.setOnClickListener(){
            val email = email_et.text.toString()
            val password = password_et.text.toString()

            login(email, password)
        }
    }
    private fun login(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    //if user is logged in successfully, go to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    //user is failed to login
                    Toast.makeText(this, "failed to login", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{

            }
    }
}