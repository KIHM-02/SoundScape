package com.example.soundscape.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.soundscape.model.Administrador
import com.example.soundscape.model.Cliente
import com.example.soundscape.view.HomeAdmin
import com.example.soundscape.view.HomeUser
import java.time.LocalDate
import kotlin.reflect.KClass

object DataUsers
{
    val adminList = ArrayList<Administrador>()
    val clientList = ArrayList<Cliente>()

    val actualDate = LocalDate.now()

    //Metodos para poder agregar, buscar y eliminar administradores
    fun addAdmin(admin: Administrador) {
        adminList.add(admin)
        Log.d("DataUsers", "Admin agregado: $admin")
    }

    fun findAdminByName(email: String): Administrador? {
        return adminList.find { it.email == email }
    }

    fun removeAdminByName(email: String): Boolean {
        return adminList.removeIf { it.email == email }
    }


    // Metodos para agregar, buscar y eliminar clientes
    fun addClient(client: Cliente) {
        clientList.add(client)
        updateDate(client.email)
        Log.d("DataUsers", "Cliente agregado: $client")
    }

    fun findClientByEmail(email: String): Cliente?
    {
        return clientList.find { it.email == email }
    }

    fun removeClientByEmail(email: String): Boolean {
        return clientList.removeIf { it.email == email }
    }

    //Functions to get the date
    fun getDay(): String {
        return actualDate.dayOfMonth.toString()
    }

    fun getMonth(): String {
        return actualDate.month.toString()
    }

    fun anioYearest(): String {
        return actualDate.year.toString()
    }

    //Metodo para determinar si es un admin o un cliente
    fun isClient(email:String, password:String, name: String, age: Int): Boolean
    {
        try {
            val domain = email.split("@")

            if(domain[1] == "soundscape.com")
            {
                addAdmin(Administrador("Administrador",name, age, email, password, "default"))
                return true
            }
            else
            {
                addClient(Cliente("Cliente", name, age, email, password, "none", "none", "none"))
                updateDate(email)
                return true
            }
        } catch (e: Exception) {
            Log.e("DataUsers", "Error al agregar el usuario: $e")
            return false
        }
    }

    //Metodos para actualizar las fechas de inicio de cuenta para cliente

    fun updateDate(email: String)
    {
        val clientPosition = clientList.indexOfFirst{it.email == email}  //Retorna posicion del correo
        Log.d("DataUsers", "Posición del cliente: $clientPosition")
        clientList[clientPosition].day = getDay()
        clientList[clientPosition].month = getMonth()
        clientList[clientPosition].year = anioYearest()
        /*
        Log.d("DataUsers", "Datos del cliente agregado: " +
                "${clientList[clientPosition].email}\n" +
                "${clientList[clientPosition].day}")
         */
    }


    //Metodo para encontrar al cliente y validar contraseña
    fun verifyUser(email: String, password: String): Pair<Boolean, KClass<out Any>?>
    {
        val domain = email.split("@")

        return if(domain[1] == "soundscape.com") {
            val admin = findAdminByName(email)
            if (admin != null && admin.password == password) { Pair(true, HomeAdmin::class) }
            else { Pair(false, null) }
        }
        else
        {
            val client = findClientByEmail(email)
            if(client != null && client.password == password){ Pair(true, HomeUser::class) }
            else{ Pair(false, null) }

        }
    }
}