package com.example.catapp.cat.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatProperty (val id : String, val url : String): Parcelable