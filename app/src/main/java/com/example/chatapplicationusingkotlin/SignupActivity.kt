package com.example.chatapplicationusingkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    private lateinit var username_et: EditText
    private lateinit var email_et: EditText
    private lateinit var password_et: EditText
    private lateinit var login_btn: Button
    private lateinit var signup_btn: Button
    private lateinit var databasereference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        username_et = findViewById(R.id.username_et)
        email_et = findViewById(R.id.email_et)
        password_et = findViewById(R.id.password_et)
        login_btn = findViewById(R.id.login_btn)
        signup_btn = findViewById(R.id.signup_btn)

        //ActionBar
        supportActionBar?.hide()

        //configure firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        login_btn.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signup_btn.setOnClickListener(){
            val username = username_et.text.toString()
            val email = email_et.text.toString()
            val password = password_et.text.toString()

            signUp(username,email, password)
        }
    }
    //SignUp処理
    private fun signUp(name: String,email: String, password: String){
        //signup firebase
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                //データベースへユーザー登録
                if(task.isSuccessful) {
                    addUserToDatabase(name, email, firebaseAuth.currentUser?.uid!!)
                    Toast.makeText(this, "signup success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "Failed to signup", Toast.LENGTH_SHORT).show()
                }

            }
    }
    //FirebaseAuthのデータベースにユーザーを登録
    private fun addUserToDatabase(name: String, email: String, uid: String){
        databasereference = Firebase.database.reference
        databasereference.child("user").child(uid).setValue(User(name, email, uid))


    }
}