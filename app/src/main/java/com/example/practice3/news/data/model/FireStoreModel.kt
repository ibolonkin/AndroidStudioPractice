package com.example.practice3.news.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
class StringFireStoreModel(val stringValue: String?)

@Keep
@Serializable
class BooleanFireStoreModel(val booleanValue: Boolean?)

@Keep
@Serializable
class NumberFireStoreModel(val integerValue: Int?)
