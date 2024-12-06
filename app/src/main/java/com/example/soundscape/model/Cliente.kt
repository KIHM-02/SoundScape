package com.example.soundscape.model

class Cliente(
    rol: String,
    nombre: String,
    edad: Int,
    email: String,
    password: String,
    var day: String,
    var month: String,
    var year: String,
    var birth: String
): Usuario("Cliente", nombre, edad, email, password)