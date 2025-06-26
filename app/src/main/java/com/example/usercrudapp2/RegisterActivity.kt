package com.example.usercrudapp2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        db = UserDatabaseHelper(this)

        val username = findViewById<EditText>(R.id.etUsernameRegister)
        val password = findViewById<EditText>(R.id.etPasswordRegister)
        val btnRegister = findViewById<Button>(R.id.btnRegisterUser)

        btnRegister.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            if (user.isBlank() || pass.isBlank()) {
                Toast.makeText(this, "Campos obrigatórios", Toast.LENGTH_SHORT).show()
            } else {
                val success = db.registerUser(user, pass)
                if (success) {
                    Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
