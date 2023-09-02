package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Forgetpassword2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpassword2)

    }
    public fun verWaButton(view: View) {
        val intent = Intent(this, Forgetpassword::class.java)
        startActivity(intent)
    }
    public fun kembaliButton(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}