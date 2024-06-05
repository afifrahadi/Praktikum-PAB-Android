package com.l0122006.afifimam.week10

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
    var name: String? = null,
    var nim: String? = null,
    var email: String? = null,
    var age: Int = 0,
    var phoneNumber: String? = null,
    var gender: Boolean = false,
    var profilePictureUrl : String? = null
) : Parcelable
