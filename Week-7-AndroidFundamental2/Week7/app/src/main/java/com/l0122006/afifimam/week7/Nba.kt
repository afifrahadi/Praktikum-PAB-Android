package com.l0122006.afifimam.week7

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nba(
    val name: String,
    val desc: String,
    val img: Int
):Parcelable
