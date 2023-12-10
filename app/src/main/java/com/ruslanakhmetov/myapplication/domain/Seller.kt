package com.ruslanakhmetov.myapplication.domain


data class Seller(
    val id: Long,
    val name: String,
    val transactionSource: TransactionSource
    )
