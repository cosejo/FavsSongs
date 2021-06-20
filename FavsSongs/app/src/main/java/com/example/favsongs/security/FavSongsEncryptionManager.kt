package com.example.favsongs.security

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class FavSongsEncryptionManager: EncryptionManager {

    private val DEFAULTKEY = "ASDFGHJKLASDFGHJ"

    /**
     * Encryption the input String with AES
     * @param input String to be encrypted
     * @return Encrypted String
     */
    override fun encrypt(input: String): String {
        val cipher = Cipher.getInstance("AES")

        val keySpec = SecretKeySpec(DEFAULTKEY.toByteArray(),"AES")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)

        val encrypt = cipher.doFinal(input.toByteArray())
        return Base64.encode(encrypt, Base64.DEFAULT).toString()
    }

    /**
     * Decryption the input String with AES
     * @param input String to be decrypted
     * @return Decrypted String
     */
    override fun decrypt(input: String): String {
        val cipher = Cipher.getInstance("AES")

        val keySpec = SecretKeySpec(DEFAULTKEY.toByteArray(),"AES")
        cipher.init(Cipher.DECRYPT_MODE, keySpec)

        val decrypt = cipher.doFinal(Base64.decode(input, Base64.DEFAULT))
        return String(decrypt)
    }
}