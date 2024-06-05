package com.l0122006.afifimam.linelite

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Call(
    val name: String,
    val desc: String,
    val img: Int
) :Parcelable
