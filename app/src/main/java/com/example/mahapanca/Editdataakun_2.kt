package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Editdataakun_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editdataakun2)
    }
    public fun backButton(view: View) {
        val intent = Intent(this, Manage_User::class.java)
        startActivity(intent)
    }
    public fun aksi1(view: View) {
        val intent = Intent(this, Editdataakun::class.java)
        startActivity(intent)
    }
}