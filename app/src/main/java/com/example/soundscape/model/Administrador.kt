package com.example.soundscape.model

class Administrador(
    rol: String,
    nombre: String,
    edad: Int,
    email: String,
    password: String,
    var auditoria: String
) : Usuario("Administrador", nombre, edad, email, password)