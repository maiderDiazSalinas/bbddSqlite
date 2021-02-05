package com.example.bbddsqlite

class Cliente(var id:Long, var nombre:String){
    constructor(nombre:String):this(0,nombre){}
    constructor():this(0,""){}
}