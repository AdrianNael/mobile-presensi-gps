package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Changepassword2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepassword2)
    }
    public fun konfirmasiButton(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
    public fun batalButton(view: View) {
        val intent = Intent(this, Changepassword::class.java)
        startActivity(intent)
    }
}