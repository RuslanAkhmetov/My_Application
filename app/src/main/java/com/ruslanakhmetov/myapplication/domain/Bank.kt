package com.ruslanakhmetov.myapplication.domain

//Названия банков и номер отправителя
data class Bank(
    val id: Long,
    val name: String,
    val smsAddress: String
)