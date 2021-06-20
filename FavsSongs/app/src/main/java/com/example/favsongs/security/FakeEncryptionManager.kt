package com.example.favsongs.security

class FakeEncryptionManager : EncryptionManager {
    override fun encrypt(input: String): String {
        return input;
    }

    override fun decrypt(input: String): String {
        return input;
    }
}