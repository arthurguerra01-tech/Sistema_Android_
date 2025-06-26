package com.example.usercrudapp2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = UserDatabaseHelper(this)

        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val login = findViewById<Button>(R.id.btnLogin)
        val register = findViewById<Button>(R.id.btnRegister)


        login.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (user.isBlank() || pass.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if (db.checkLogin(user, pass)) {
                startActivity(Intent(this, UserListActivity::class.java))
            } else {
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
