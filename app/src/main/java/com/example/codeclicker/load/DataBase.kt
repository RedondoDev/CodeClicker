package com.example.codeclicker.load

import android.app.Activity
import com.example.codeclicker.R
import com.example.codeclicker.start.YourCharacter
import com.google.firebase.Firebase
import com.google.firebase.database.database

class DataBase(activity: Activity, yourCharacter: YourCharacter?, userId: String) {

    val dataBase = Firebase.database(activity.getString(R.string.dbUrl))

    fun saveCharacter(yourCharacter: YourCharacter, userId: String) {
        dataBase.getReference("users/$userId/character").push().setValue(yourCharacter)
    }

    fun getCharacter(): YourCharacter {
        // Pedir uno de verdad
        return YourCharacter(1,"a", "Java", 1, 0, false)
    }

}