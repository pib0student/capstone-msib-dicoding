package com.dicoding.timsaw.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.timsaw.databinding.ActivityLoginBinding
import com.dicoding.timsaw.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        supportActionBar?.hide()

        loginUsername()
    }

    private fun loginUsername() {

        val phone = loginBinding.phone
        val password = loginBinding.password
        val loginBtn = loginBinding.loginBtn

        phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (android.util.Patterns.PHONE.matcher(phone.text.toString())
                        .matches()
                ) {
                    loginBtn.isEnabled = true
                } else {
                    loginBtn.isEnabled = false
                    phone.error = "Invalid Username"
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })
        loginBtn.setOnClickListener {
            phone.setText("")
            password.setText("")
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Login is Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}