package com.example.mahapanca

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class Recap_Status : AppCompatActivity() {
    private lateinit var tvSelectDate: TextView
    private lateinit var etSelectDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recapstatus)
        tvSelectDate = findViewById(R.id.tvSelectDate)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        tvSelectDate.setOnClickListener {
            val dialog = DatePickerDialog(this@Recap_Status,
                { _, year, month, dayOfMonth ->
                    var month = month
                    month += 1
                    val date = "$dayOfMonth/$month/$year"
                    tvSelectDate.text = date
                }, year, month, day
            )
            dialog.show()
        }



    }
    public fun selectButton(view: View) {
        val intent = Intent(this, Recap_Status_2::class.java)
        startActivity(intent)
    }
    public fun backButton(view: View) {
        val intent = Intent(this, Dashboard_Hr::class.java)
        startActivity(intent)
    }

}