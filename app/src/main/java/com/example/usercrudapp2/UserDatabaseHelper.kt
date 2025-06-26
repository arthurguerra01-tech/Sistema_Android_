package com.example.usercrudapp2


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UserDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun registerUser(username: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        return db.insert("users", null, values) > 0
    }

    fun checkLogin(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", arrayOf(username, password))
        val result = cursor.count > 0
        cursor.close()
        return result
    }

    fun getAllUsers(): List<String> {
        val list = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users", null)
        while (cursor.moveToNext()) {
            list.add("${cursor.getInt(0)} - ${cursor.getString(1)}")
        }
        cursor.close()
        return list
    }

    fun deleteUser(id: Int): Boolean {
        val db = writableDatabase
        return db.delete("users", "id=?", arrayOf(id.toString())) > 0
    }
}
