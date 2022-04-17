package com.diegocastro.kotlinbdroom01.room

import androidx.room.*

@Dao
interface PersonaDAO {
    @Query("Select * From persona") //debe ser el nombre de la tabla
    fun obtenerListaPersonas():List<Persona> //Obtiene la lista

    @Query("Select * From persona Where id=:id")
    fun buscarPersona(id:Int):Persona

    @Update
    fun actualizar(persona: Persona)

    @Insert
    fun insertar(persona: Persona):Long//se hace retornar un long para poder saber si se inserto en la bd

    @Delete
    fun borrar(persona: Persona)

}