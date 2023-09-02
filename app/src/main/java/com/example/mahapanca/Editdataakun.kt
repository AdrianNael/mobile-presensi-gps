package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Editdataakun : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editdataakun)
    }
    fun konfirmasiButton(view: View?) {
        val intent = Intent(this, Dashboard_Hr::class.java)
        startActivity(intent)
    }
    fun backButton(view: View?) {
        val intent = Intent(this, Editdataakun_2::class.java)
        startActivity(intent)
    }
}