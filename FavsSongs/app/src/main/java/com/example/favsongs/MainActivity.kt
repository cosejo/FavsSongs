package com.example.favsongs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.favsongs.login.view.LoginActivity
import com.example.favsongs.search.view.SearchActivity
import com.example.favsongs.search.view.SearchActivity.SEARCH_PARAMETER
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            search(searchEditText.text.toString())
        }
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

    private fun search(searchString: String) {
        if (searchString.isEmpty()){
            Toast.makeText(this, R.string.search_invalid_input, Toast.LENGTH_SHORT).show()
        }
        else {
            openSearchActivity(searchString)
        }
    }

    private fun openSearchActivity(searchString: String) {
        val searchIntent = Intent(this, SearchActivity::class.java)
        searchIntent.putExtra(SEARCH_PARAMETER, searchString)
        startActivity(searchIntent)
    }

    private fun openProfileActivity() {
        val profileIntent = Intent(this, LoginActivity::class.java)
        startActivity(profileIntent)
    }
}