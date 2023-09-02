package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Dashboard_user : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_kehadiran_user)
    }
    public fun masukButton(view: View) {
        val intent = Intent(this, Verifikasilokasi::class.java)
        startActivity(intent)
    }
    public fun pulangButton(view: View) {
        val intent = Intent(this, Verifikasilokasi::class.java)
        startActivity(intent)
    }
    public fun logout(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}