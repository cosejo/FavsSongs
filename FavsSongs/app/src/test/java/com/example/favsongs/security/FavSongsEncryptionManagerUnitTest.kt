package com.example.favsongs.security

import org.junit.Assert
import org.junit.Test

class FavSongsEncryptionManagerUnitTest {

    @Test
    fun encrypt_success() {
        val value = "verySecurePassword"
        val expectedResult = "[B@b78b50b"
        val encryptionManager = FavSongsEncryptionManager()

        val encryptedValue = encryptionManager.encrypt(value)

        Assert.assertEquals(encryptedValue, expectedResult);
    }

    @Test
    fun encrypt_successWithTwoDifferentValues() {
        val value = "verySecurePassword"
        val secondValue = "anotherVerySecurePassword"
        val encryptionManager = FavSongsEncryptionManager()

        val encryptedValue = encryptionManager.encrypt(value)
        val secondEncryptedValue = encryptionManager.encrypt(secondValue)

        Assert.assertNotEquals(encryptedValue, secondEncryptedValue);
    }

    @Test
    fun decrypt_success() {
        val valueEncrypted = "[B@a43e2cc"
        val expectedResult = "qwerty123$"
        val encryptionManager = FavSongsEncryptionManager()

        val decryptedValue = encryptionManager.decrypt(valueEncrypted)

        Assert.assertNotEquals(decryptedValue, expectedResult);
    }

    @Test
    fun decrypt_successdWithTwoDifferentValues() {
        val valueEncrypted = "[B@a43e2cc"
        val secondValueEncrypted = "[B@b78b50b"
        val encryptionManager = FavSongsEncryptionManager()

        val decryptedValue = encryptionManager.decrypt(valueEncrypted)
        val secondDecryptedValue = encryptionManager.encrypt(secondValueEncrypted)

        Assert.assertNotEquals(decryptedValue, secondDecryptedValue);
    }
}