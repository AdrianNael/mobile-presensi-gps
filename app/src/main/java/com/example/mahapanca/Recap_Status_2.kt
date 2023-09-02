package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Recap_Status_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recapstatus2)
    }
    public fun backButton(view: View) {
        val intent = Intent(this, Recap_Status::class.java)
        startActivity(intent)
    }
    public fun selectButton(view: View) {
        val intent = Intent(this, Dashboard_Hr::class.java)
        startActivity(intent)
    }
}