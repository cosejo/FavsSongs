package com.example.favsongs.login.model

interface User {
    fun getId(): Int
    fun getName(): String
    fun getPicture(): String
    fun getPassword(): String
}