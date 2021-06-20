package com.example.favsongs.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.favsongs.R
import com.example.favsongs.login.view.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                openProfileActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openProfileActivity() {
        val profileIntent = Intent(this, LoginActivity::class.java)
        startActivity(profileIntent)
    }
}