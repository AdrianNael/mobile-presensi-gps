package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Manage_Office : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manageoffice)
    }
    public fun backButton(view: View) {
        val intent = Intent(this, Dashboard_Hr::class.java)
        startActivity(intent)
    }
    public fun konfirmasiButton(view: View) {
        val intent = Intent(this, Dashboard_Hr::class.java)
        startActivity(intent)
    }
}