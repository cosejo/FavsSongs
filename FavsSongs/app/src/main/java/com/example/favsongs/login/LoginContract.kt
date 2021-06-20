package com.example.favsongs.login

class LoginContract {
    interface View {
        fun successfulLogin(id: Int)
        fun errorLogin()
        fun showLoadingView()
        fun hideLoadingView()
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}