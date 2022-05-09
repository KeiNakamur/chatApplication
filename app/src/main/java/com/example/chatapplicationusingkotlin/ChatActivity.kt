package com.example.chatapplicationusingkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView = findViewById(R.id.recyclerview_chat)
        messageBox = findViewById(R.id.messageBox)
        sendImage = findViewById(R.id.sendImage)

        val name = intent.getStringExtra("name")
        val userId = intent.getStringExtra("userId")

        supportActionBar?.title = name


    }
}