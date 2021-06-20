package com.example.favsongs.login

import com.example.favsongs.db.dao.UserDAO
import com.example.favsongs.security.EncryptionManager
import kotlinx.coroutines.*

class LoginPresenter(private val view: LoginContract.View, private val userDao: UserDAO, private val encryptionManager: EncryptionManager) : LoginContract.Presenter {
    private val job = Job()
    private val scopeMainThread = CoroutineScope(job + Dispatchers.Main)
    private val scopeIO = CoroutineScope(job + Dispatchers.IO)

    override fun login(username: String, password: String) {
        view.showLoadingView()
        scopeIO.launch {
            val user = userDao.getUserByName(username)
            scopeMainThread.launch {
                if (user == null) {
                    view.hideLoadingView()
                    view.errorLogin()
                }
                else {
                    validateUserPassword(password, user.getPassword(), user.getId())
                }
            }
        }
    }

    private fun validateUserPassword(inputPassword: String, userPassword: String, id: Int) {
        val encryptedPassword = encryptionManager.encrypt(inputPassword)
        view.hideLoadingView()
        if (userPassword == encryptedPassword){
            view.successfulLogin(id)
        }
        else {
            view.errorLogin()
        }
    }
}