package com.example.newsapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityLoginBinding
import com.example.newsapp.domain.model.User
import com.example.newsapp.presentation.main.MainActivity
import com.example.newsapp.presentation.register.RegisterActivity
import com.example.newsapp.utill.Msg
import com.example.newsapp.utill.RequestCodes
import com.example.newsapp.utill.ResultCode
import com.example.newsapp.utill.extenctions.alert
import com.example.newsapp.utill.extenctions.isValidEmail
import com.example.newsapp.utill.extenctions.validateInput
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private val vm by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        vm.loginResult.observe(this, Observer { observerLogin(it) })

    }

    private fun observerLogin(result: Result<User>) {
        if (result.isSuccess){
            gotoMain()
        }else{
            alert(
                Msg.ALERT,
                Msg.ALERT_LOGIN_FAILED
            ) {
                positiveButton(Msg.BUTTON_OK) {
                }
            }.show()
        }
    }

    private fun initUI() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)

        binding.textSignUp.setOnClickListener(this)
        binding.buttonSignIn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_sign_up -> gotoSignUp()
            R.id.button_sign_in -> if (isValidForm()) {
               login()
            }
        }
    }

    private fun login() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        vm.loginUser(context = this, email = email, password = password)
    }

    private fun isValidForm(): Boolean {

        val isEmail = binding.editEmail.validateInput(
            context = this,
            textInputLayout = binding.inputEmail
        ) { s -> s.isValidEmail() }

        val isPassword = binding.editPassword.validateInput(
            context = this,
            textInputLayout = binding.inputPassword
        ) { s -> s.length >= 6 }

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

        return isEmail && isPassword
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent).also {
            finish()
        }
    }

    private fun gotoSignUp() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivityForResult(intent, RequestCodes.REGISTRATION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RequestCodes.REGISTRATION && resultCode == ResultCode.RESULT_NAV_MAIN) {
            gotoMain()
        }
    }
}