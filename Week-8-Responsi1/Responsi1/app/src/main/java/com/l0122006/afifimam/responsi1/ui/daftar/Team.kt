package com.l0122006.afifimam.responsi1.ui.daftar

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val name: String,
    val desc: String,
    val img: Int
) : Parcelable