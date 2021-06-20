package com.example.favsongs.login

import com.example.favsongs.db.dao.UserDAO
import com.example.favsongs.db.entity.UserEntity
import com.example.favsongs.security.EncryptionManager
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class LoginPresenterUnitTest {



    @Test
    fun login_failed_userNotFound() {
        val username = "NotFoundUser"
        val password = "NotFoundUserPass"
        val view = mock(LoginContract.View::class.java)
        val userDao = mock(UserDAO::class.java)
        val encryptionManager = mock(EncryptionManager::class.java)
        val loginPresenter = LoginPresenter(view, userDao, encryptionManager)

        runBlocking {
            `when`(userDao.getUserByName(ArgumentMatchers.anyString())).thenReturn(null)
            loginPresenter.login(username, password)
        }

        verify(view).hideLoadingView()
        verify(view).errorLogin()
    }

    @Test
    fun login_failed_wrongPassword() {
        val username = "WrongPasswordUser"
        val password = "Password"
        val wrongPassword = "WrongPassword"
        val view = mock(LoginContract.View::class.java)
        val userDao = mock(UserDAO::class.java)
        val user = mock(UserEntity::class.java)
        `when`(user.getPassword()).thenReturn(password)
        val encryptionManager = mock(EncryptionManager::class.java)
        val loginPresenter = LoginPresenter(view, userDao, encryptionManager)

        runBlocking {
            `when`(userDao.getUserByName(ArgumentMatchers.anyString())).thenReturn(user)
            loginPresenter.login(username, wrongPassword)
        }

        verify(view).hideLoadingView()
        verify(view).errorLogin()
    }

    @Test
    fun login_success() {
        val username = "SuccessfulUser"
        val password = "SuccessfulPassword"
        val view = mock(LoginContract.View::class.java)
        val userDao = mock(UserDAO::class.java)
        val user = mock(UserEntity::class.java)
        `when`(user.getPassword()).thenReturn(password)
        val encryptionManager = mock(EncryptionManager::class.java)
        val loginPresenter = LoginPresenter(view, userDao, encryptionManager)

        runBlocking {
            `when`(userDao.getUserByName(ArgumentMatchers.anyString())).thenReturn(user)
            loginPresenter.login(username, password)
        }
        verify(view).hideLoadingView()
        verify(view).successfulLogin(ArgumentMatchers.anyInt())
    }
}