package com.hdsturkey.yalovabsm404.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hdsturkey.yalovabsm404.databinding.ActivityLoginBinding
import com.hdsturkey.yalovabsm404.utils.Constants
import com.hdsturkey.yalovabsm404.utils.SharedPreferencesHelper
import com.hdsturkey.yalovabsm404.utils.isValidEmail
import com.hdsturkey.yalovabsm404.utils.startActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setListeners()
    }

    private fun setListeners() {
        mBinding.btnLogin.setOnClickListener {
            if (isInputsValid()) {
                if (mBinding.chkRememberMe.isChecked) {
                    saveUserAndNavigateToNextScreen()
                } else {
                    navigateUserToNextScreen()
                }
            }
        }
    }

    private fun isInputsValid(): Boolean {
        mBinding.tilUserMail.error = null
        mBinding.tilPassword.error = null

        val userMail = mBinding.etUserMail.text
        if (userMail.isNullOrBlank()) {
            mBinding.tilUserMail.error = "Please enter your email address"
            return false
        }

        if (userMail.toString().isValidEmail().not()) {
            mBinding.tilUserMail.error = "Please enter a valid email address"
            return false
        }

        val userPassword = mBinding.etPassword.text
        if (userPassword.isNullOrBlank()) {
            mBinding.tilPassword.error = "Please enter your password"
            return false
        }

        return true
    }

    private fun saveUserAndNavigateToNextScreen() {
        saveUser()
        navigateUserToNextScreen()
    }

    private fun saveUser() {
        val userEmail = mBinding.etUserMail.text.toString()
        val userPassword = mBinding.etPassword.text.toString()
        SharedPreferencesHelper.putString(Constants.PREF_USER_EMAIL, userEmail)
        SharedPreferencesHelper.putString(Constants.PREF_USER_PASSWORD, userPassword)
    }

    private fun navigateUserToNextScreen() {
        startActivity(UserActivity::class.java)
        finish() //so important. It will kill this activity so when clicked on back button in next article this activity  won't appear
    }

}