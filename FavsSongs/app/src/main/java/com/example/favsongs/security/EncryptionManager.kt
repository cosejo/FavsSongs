package com.example.favsongs.security

interface EncryptionManager {
    fun encrypt(input: String): String
    fun decrypt(input: String): String
}