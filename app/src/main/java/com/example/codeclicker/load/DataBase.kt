package com.example.codeclicker.load

import android.app.Activity
import com.example.codeclicker.R
import com.google.firebase.Firebase
import com.google.firebase.database.database

class DataBase (activity: Activity) {

    val dataBase = Firebase.database(activity.getString(R.string.dbUrl))


}