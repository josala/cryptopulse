package com.josala.core.model

import java.io.Serializable

data class Crypto(
    val id: Int,
    val name: String,
    val price: String
) : Serializable