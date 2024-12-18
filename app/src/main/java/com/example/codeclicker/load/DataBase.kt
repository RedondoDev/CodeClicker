package com.example.codeclicker.load

import android.app.Activity
import android.util.Log
import com.example.codeclicker.R
import com.example.codeclicker.start.YourCharacter
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.tasks.await

class DataBase(val activity: Activity, val userId: String) {

    val dataBase = Firebase.database(activity.getString(R.string.dbUrl))

    fun saveCharacter(yourCharacter: YourCharacter) {
        dataBase.getReference("users/$userId/character").setValue(yourCharacter)
    }

    fun updateMoney(money: Int) {
        dataBase.getReference("users/$userId/character").child("money").setValue(money)
    }

    fun updateClics(clics: Int) {
        dataBase.getReference("users/$userId/character").child("clics").setValue(clics)
    }

    fun updateCopilot(copilot: Boolean) {
        dataBase.getReference("users/$userId/character").child("copilot").setValue(copilot)
    }

    suspend fun getCharacter(): YourCharacter? {
        return try {
            val snapshot = dataBase.getReference("users/$userId/character").get().await()
            snapshot.getValue(YourCharacter::class.java)
        } catch (e: Exception) {
            Log.e("DataBase", "Error getting character", e)
            null
        }
    }

    suspend fun getMoney(): Int? {
        return try {
            val snapshot = dataBase.getReference("users/$userId/character/money").get().await()
            snapshot.getValue(Int::class.java)
        } catch (e: Exception) {
            Log.e("DataBase", "Error getting character", e)
            null
        }
    }

}