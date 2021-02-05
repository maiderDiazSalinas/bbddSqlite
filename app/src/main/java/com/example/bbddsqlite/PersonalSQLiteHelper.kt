package com.example.bbddsqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class PersonalSQLiteHelper(var context: Context): SQLiteOpenHelper(context, DB_NAME, null, VERSION ) {


    override fun onCreate(db: SQLiteDatabase) {
        dropAndCreate(db)
    }

    protected fun dropAndCreate(db: SQLiteDatabase) {
        db.execSQL("drop table if exists $CLIENT_TABLE;")
        createTables(db)
    }

    protected fun createTables(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + CLIENT_TABLE + " (" +
                    CLIENT_ID + " integer primary key autoincrement " +
                    "not null," +
                    CLIENT_NAME + " text not null" +
                    ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Toast.makeText(context,"no hay nuevas versiones",Toast.LENGTH_SHORT).show()
    }

    companion object {
        val DB_NAME="miBBDD"
        val VERSION= 1
        val CLIENT_ID="client_id"
        val CLIENT_NAME="client_name"
        val CLIENT_TABLE="client"
    }
}

