package com.example.mahapanca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Manage_User : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manageuser)
    }
    public fun createaccountButton(view: View) {
        val intent = Intent(this, Createnewaccount::class.java)
        startActivity(intent)
    }
    public fun button3(view: View) {
        val intent = Intent(this, Dashboard_Hr::class.java)
        startActivity(intent)
    }
    public fun editdataakun(view: View) {
        val intent = Intent(this, Editdataakun_2::class.java)
        startActivity(intent)
    }
}