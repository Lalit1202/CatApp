package com.example.catapp.cat.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatData(val id: String, val url: String) : Parcelable