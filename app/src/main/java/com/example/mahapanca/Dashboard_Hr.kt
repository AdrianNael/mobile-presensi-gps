package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View

class Dashboard_Hr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_kehadiran)
    }
    public fun RSButton(view: View) {
        val intent = Intent(this, Recap_Status::class.java)
        startActivity(intent)
    }
    public fun CButton(view: View) {
        val intent = Intent(this, Chart::class.java)
        startActivity(intent)
    }
    public fun MUButton(view: View) {
        val intent = Intent(this, Manage_User::class.java)
        startActivity(intent)
    }
    public fun MOButton(view: View) {
        val intent = Intent(this, Manage_Office::class.java)
        startActivity(intent)
    }
    public fun MasukButton(view: View) {
        val intent = Intent(this, Verifikasilokasi::class.java)
        startActivity(intent)
    }
    public fun PulangButton(view: View) {
        val intent = Intent(this, Verifikasilokasi::class.java)
        startActivity(intent)
    }
    public fun logout(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}