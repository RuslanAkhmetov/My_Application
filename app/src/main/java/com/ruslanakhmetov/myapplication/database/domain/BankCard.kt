package com.ruslanakhmetov.myapplication.database.domain

data class BankCard(
    val bankName: String,
    val cardPan: String,
    var balance: Double
    )

