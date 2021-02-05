package com.example.bbddsqlite

import android.app.Application
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.util.ArrayList

class Modelo (val miAplicacion: Aplicacion){
    private val db:SQLiteDatabase
    var listado: MutableList<Cliente> =mutableListOf()

    init{
        val helper = PersonalSQLiteHelper(miAplicacion)
        db=helper.writableDatabase
    }

    fun CargarLista() {
        val personCursor = db.query(PersonalSQLiteHelper.CLIENT_TABLE,
            arrayOf(PersonalSQLiteHelper.CLIENT_ID, PersonalSQLiteHelper.CLIENT_NAME),null,null,null,null,null)
        personCursor.moveToFirst()

        if (!personCursor.isAfterLast) {
            do {
                val id = personCursor.getLong(0)
                val name = personCursor.getString(1)
                listado.add(Cliente(id,name))
            } while (personCursor.moveToNext())
        }
        personCursor.close()

    }

    fun Insertar(c:Cliente) {
        //assert(null != c)

        val values = ContentValues()
        values.put(PersonalSQLiteHelper.CLIENT_NAME, c.nombre)

        c.id = db.insert(PersonalSQLiteHelper.CLIENT_TABLE, null, values)

        listado.add(c)
    }

    fun Modificar(c: Cliente) {
        //assert(null != p)
        val values = ContentValues()
        values.put(PersonalSQLiteHelper.CLIENT_NAME, c.nombre)
        val id = c.id
        val where = String.format("%s = %d",
                    PersonalSQLiteHelper.CLIENT_ID, id)

        db.update(PersonalSQLiteHelper.CLIENT_TABLE, values, where, null)
        for (cliente in listado) {
            if (c.id === cliente.id) {
                cliente.nombre = c.nombre
                break
            }
        }
    }

    fun Borrar(id:Long) {
        val where = String.format("%s = %s",
                PersonalSQLiteHelper.CLIENT_ID, id)
        db.delete(PersonalSQLiteHelper.CLIENT_TABLE, where, null)
        for (cliente in listado) {
            if (cliente.id == id) {
                listado.remove(cliente)
            }
        }
    }

    fun Buscar(id: Long): Cliente {
        for (cliente in listado) {
            if (cliente.id == id) {
                return cliente
            }
        }
        return Cliente()
    }
}
