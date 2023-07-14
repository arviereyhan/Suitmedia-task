package com.example.suitmediaproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var Name: String? = null,
): Parcelable
