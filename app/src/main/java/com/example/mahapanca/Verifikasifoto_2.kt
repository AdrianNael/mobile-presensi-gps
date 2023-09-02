package com.example.mahapanca

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class Verifikasifoto_2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasifoto2)
    }

    fun konfirmasiButton(view: View?) {
        val intent = Intent(this, Dashboard_user::class.java)
        startActivity(intent)
    }

    fun batalButton(view: View?) {
        val intent = Intent(this, Verifikasilokasi::class.java)
        startActivity(intent)
    }
}
