package com.example.codeclicker.load

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    suspend fun getCharacter(): YourCharacter? {
        return try {
            val snapshot = dataBase.getReference("users/$userId/character").get().await()
            snapshot.getValue(YourCharacter::class.java)
        } catch (e: Exception) {
            Log.e("DataBase", "Error getting character", e)
            null
        }
    }

    fun updateCharacter(character: YourCharacter) {

        dataBase.getReference("users/$userId/character")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    println("On data change")
                    val value = snapshot.getValue<YourCharacter>()
                    if (value != null) {
                        character.name = value.name
                        character.selectedCharacterIndex = value.selectedCharacterIndex
                        character.language = value.language
                        character.clics = value.clics
                        character.money = value.money
                        character.copilot = value.copilot
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })
    }

}