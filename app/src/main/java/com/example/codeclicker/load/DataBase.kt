package com.example.codeclicker.load

import android.app.Activity
import android.util.Log
import com.example.codeclicker.R
import com.example.codeclicker.start.YourCharacter
import com.google.firebase.Firebase
import com.google.firebase.database.database
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

    fun updateTimer(timer: Long) {
        dataBase.getReference("users/$userId/character").child("timer").setValue(timer)
    }

    suspend fun getTop30CharactersByMoney(): ArrayList<YourCharacter> {
        return try {
            val snapshot = dataBase.getReference("users").get().await()
            val characters = snapshot.children.mapNotNull {
                it.child("character").getValue(YourCharacter::class.java)
            }
            ArrayList(characters.sortedByDescending { it.money }.take(30))
        } catch (e: Exception) {
            Log.e("DataBase", "Error getting top characters", e)
            ArrayList()
        }
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