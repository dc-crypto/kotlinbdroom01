package com.diegocastro.kotlinbdroom01.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities =[Persona::class], version=1, exportSchema = false)
abstract class PersonaDataBase:RoomDatabase() {
    //comunica la entidad Persona con la interface Persona DAO
    //como nunca va a ser instanciada se le agrega abstract
    //definimos un personaDao y un db_persona

    abstract val personaDAO:PersonaDAO

    companion object{
        const val DATABASE_NAME="db_persona"
    }

}