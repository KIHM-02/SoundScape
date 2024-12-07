package com.example.soundscape.controller

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.soundscape.model.Administrador
import com.example.soundscape.model.Cliente
import java.time.LocalDate

object DataUsers
{
    val adminList = ArrayList<Administrador>()
    val clientList = ArrayList<Cliente>()

    @RequiresApi(Build.VERSION_CODES.O)
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun addClient(client: Cliente) {
        clientList.add(client)
        updateDate(client.email)
        Log.d("DataUsers", "Cliente agregado: $client")
    }

    fun findClientByName(email: String): Cliente? {
        return clientList.find { it.email == email }
    }

    fun removeClientByName(email: String): Boolean {
        return clientList.removeIf { it.email == email }
    }

    //Functions to get the date
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDay(): String {
        return actualDate.dayOfMonth.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonth(): String {
        return actualDate.month.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun anioYearest(): String {
        return actualDate.year.toString()
    }


    //Metodos para actualizar las fechas de inicio de cuenta para cliente
    @RequiresApi(Build.VERSION_CODES.O)
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
}