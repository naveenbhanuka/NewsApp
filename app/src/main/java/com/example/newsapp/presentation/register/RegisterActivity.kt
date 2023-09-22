package com.example.newsapp.presentation.register

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityRegisterBinding
import com.example.newsapp.utill.Msg
import com.example.newsapp.utill.ResultCode
import com.example.newsapp.utill.extenctions.alert
import com.example.newsapp.utill.extenctions.isValidEmail
import com.example.newsapp.utill.extenctions.setActionBar
import com.example.newsapp.utill.extenctions.validateInput

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)

        initUI()


    }

    private fun initUI() {
        setSupportActionBar(binding.toolbarAuthSignUp)
        supportActionBar?.setActionBar(
            this,
            getString(R.string.label_signUp),
            true
        )
        binding.buttonSignUp.setOnClickListener(this)
    }

    private fun isValidForm(): Boolean {

        val isFirstName = binding.editFirstName.validateInput(
            context = this,
            textInputLayout = binding.inputFirstName
        ) { s -> s.length >= 3 }

        val isLastName = binding.editLastName.validateInput(
            context = this,
            textInputLayout = binding.inputLastName
        ) { s -> s.length >= 3 }

        val isEmail = binding.editEmail.validateInput(
            context = this,
            textInputLayout = binding.inputEmail
        ) { s -> s.isValidEmail() }

        val isPassword = binding.editPassword.validateInput(
            context = this,
            textInputLayout = binding.inputPassword
        ) { s -> s.length >= 6 }

        val isConfirmPassword = binding.editConfirm.validateInput(
            context = this,
            textInputLayout = binding.inputConfirmPassword
        ) { s -> s.length >= 6 }

        val isPasswordMatch =
            (binding.editPassword.text.toString() == binding.editConfirm.text.toString())

        if (!isFirstName) {
            alert(
                Msg.TITLE_VALID_NAME_REQ,
                Msg.VALID_NAME
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
            return false
        }
        if (!isLastName) {
            alert(
                Msg.TITLE_VALID_NAME_REQ,
                Msg.VALID_NAME
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
            return false
        }

        if (!isEmail) {
            alert(
                Msg.TITLE_VALID_EMAIL_REQ,
                Msg.VALID_EMAIL
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
            return false
        }

        if (!isPassword) {
            alert(
                Msg.TITLE_VALID_PASSWORD_REQ,
                Msg.VALID_PASSWORD
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
            return false
        }

        if (!isConfirmPassword) {
            alert(
                Msg.TITLE_VALID_PASSWORD_REQ,
                Msg.VALID_PASSWORD
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
            return false
        }

        if (!isPasswordMatch) {
            alert(
                Msg.TITLE_PASSWORD_MATCH,
                Msg.MATCH_PASSWORD
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
            return false
        }


        return isFirstName && isLastName && isEmail && isPassword && isConfirmPassword && isPasswordMatch
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_sign_up -> if (isValidForm()) {
                signUp()
            }
        }
    }

    private fun signUp() {
        setResult(ResultCode.RESULT_NAV_MAIN).also { finish() }
    }
}