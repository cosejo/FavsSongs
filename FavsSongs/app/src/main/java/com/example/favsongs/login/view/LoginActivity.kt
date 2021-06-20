package com.example.favsongs.login.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.favsongs.FavSongsApplication
import com.example.favsongs.R
import com.example.favsongs.login.LoginContract
import com.example.favsongs.login.LoginPresenter
import com.example.favsongs.security.FavSongsEncryptionManager
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity: AppCompatActivity(), LoginContract.View {

    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this, (application as FavSongsApplication).database.userDao(), FavSongsEncryptionManager())

        loginButton.setOnClickListener {
            login(usernameEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    fun login(username: String, password: String) {
        presenter?.login(username, password)
    }

    override fun successfulLogin(id: Int ) {
        //Put it on bundle
    }

    override fun errorLogin() {
        Toast.makeText(this, "Login could not be performed", Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingView() {
//        TODO("Not yet implemented")
    }

    override fun hideLoadingView() {
//        TODO("Not yet implemented")
    }
}