package com.example.mahapanca.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("role") val role: String // Tambahkan properti 'role' pada model
)