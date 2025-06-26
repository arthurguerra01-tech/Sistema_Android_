package com.example.usercrudapp2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class UserListActivity : AppCompatActivity() {
    lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        db = UserDatabaseHelper(this)

        val listView = findViewById<ListView>(R.id.listViewUsers)
        val users = db.getAllUsers()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selected = users[position]
            val id = selected.split(" - ")[0].toInt()
            val deleted = db.deleteUser(id)
            if (deleted) {
                Toast.makeText(this, "Usuário excluído", Toast.LENGTH_SHORT).show()
                recreate()
            }
        }
    }
}
