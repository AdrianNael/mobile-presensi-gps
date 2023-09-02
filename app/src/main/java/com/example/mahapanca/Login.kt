package com.example.mahapanca

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mahapanca.api.ApiClient
import com.example.mahapanca.databinding.ActivityLoginBinding
import com.example.mahapanca.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun simulateLogin(nik: String, password: String): String? {
        // Simulasi data login statis
        val user1 = User("1234", "admin", "user")
        val user2 = User("4321", "admin", "hr")

        // Verifikasi nik dan password
        return when {
            nik == user1.nik && password == user1.password -> user1.role
            nik == user2.nik && password == user2.password -> user2.role
            else -> null
        }
    }
    data class User(val nik: String, val password: String, val role: String)

    public fun loginButton(view: View) {
        val nik = binding.editTextNik.text.toString().trim()
        val password = binding.editTextPasswrod.text.toString().trim()

        val role = simulateLogin(nik, password)

        if (role != null) {
            // Login berhasil, arahkan ke dashboard sesuai role
            val intent = when (role) {
                "user" -> Intent(this@Login, Dashboard_user::class.java)
                "hr" -> Intent(this@Login, Dashboard_Hr::class.java)
                else -> null
            }

            if (intent != null) {
                startActivity(intent)
            }
        } else {
            Toast.makeText(this@Login, "Login gagal.", Toast.LENGTH_SHORT).show()
        }
    }
//    public fun loginButton(view: View) {
//        val nik = binding.editTextNik.text.toString().trim()
//        val password = binding.editTextPasswrod.text.toString().trim()
//
//        val apiService = ApiClient.apiService
//        val call = apiService.login(nik, password)
//
//        call.enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    val loginResponse = response.body()
//                    if (loginResponse?.success == true) {
//                        // Login berhasil, lakukan tindakan sesuai kebutuhan
//                        val intent = Intent(this@Login, Dashboard_user::class.java)
//                        startActivity(intent)
//                    } else {
//                        val errorMessage = loginResponse?.message ?: "Login gagal."
//                        Toast.makeText(this@Login, errorMessage, Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    val errorMessage = "Error: ${response.code()}"
//                    Toast.makeText(this@Login, errorMessage, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                val errorMessage = "Error: ${t.message}"
//                Toast.makeText(this@Login, errorMessage, Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

    // Fungsi-fungsi lainnya...
    public fun forgetButton(view: View) {
        val intent = Intent(this, Forgetpassword2::class.java)
        startActivity(intent)
    }
    public fun changeButton(view: View) {
        val intent = Intent(this, Changepassword::class.java)
        startActivity(intent)
    }
//    public fun loginButtonHr(view: View) {
//        val intent = Intent(this, Dashboard_Hr::class.java)
//        startActivity(intent)
//    }
}


