package com.example.codeclicker.game

import android.os.Parcel
import android.os.Parcelable

data class CodeLine(
    val text: String,
    val size: Int,
    val initialX: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeInt(size)
        parcel.writeInt(initialX)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CodeLine> {
        override fun createFromParcel(parcel: Parcel): CodeLine {
            return CodeLine(parcel)
        }

        override fun newArray(size: Int): Array<CodeLine?> {
            return arrayOfNulls(size)
        }
    }
}
