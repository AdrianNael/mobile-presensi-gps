package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Forgetpassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpassword)
    }
    public fun verifyButton(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
    public fun kembaliButton(view: View) {
        val intent = Intent(this, Forgetpassword2::class.java)
        startActivity(intent)
    }
}