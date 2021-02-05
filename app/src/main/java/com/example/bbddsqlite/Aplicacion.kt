package com.example.bbddsqlite

import android.app.Application

class Aplicacion: Application() {
    lateinit var modelo: Modelo
    var listaClientes: MutableList<Cliente> = mutableListOf()

    override fun onCreate() {
        super.onCreate()
        modelo=Modelo(this)
        modelo.CargarLista()
        listaClientes=modelo.listado
    }

}


