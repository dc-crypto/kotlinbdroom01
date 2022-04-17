package com.diegocastro.kotlinbdroom01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.room.Room
import com.diegocastro.kotlinbdroom01.room.Persona
import com.diegocastro.kotlinbdroom01.room.PersonaDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var database:PersonaDataBase //aparece con error porque no ha sido inicializado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database= Room.databaseBuilder(
            //indica que queremos usar la bd en el contxto de nuestra application
            application, PersonaDataBase::class.java, PersonaDataBase.DATABASE_NAME
        ).allowMainThreadQueries().build()
        //estas líneas se evitan cuando se usa inyección de dependencias

        var progressBar:ProgressBar=findViewById(R.id.progressBar)
        var txtRut: EditText=findViewById(R.id.txtRut)
        var txtNombre: EditText=findViewById(R.id.txtNombre)
        var txtEdad: EditText=findViewById(R.id.txtEdad)
        var btnGuardar: Button=findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener{
            var rut: String= txtRut.text.toString()
            var nombre: String= txtNombre.text.toString()
            var edad: Int = txtEdad.text.toString().toInt()

            progressBar.visibility= View.VISIBLE

            //invocamos una corrutina para no bloquear el hilo ppal
            GlobalScope.launch(Dispatchers.Main) {
              var x:Long =  guardarPersona(rut,nombre,edad)
                if (x>0){
                    Toast.makeText(applicationContext,"Insertado OK", Toast.LENGTH_SHORT).show()
                }

                else{
                    Toast.makeText(applicationContext,"No Insertado", Toast.LENGTH_SHORT).show()
                }
                progressBar.visibility= View.GONE //el progressbar se oculta indep del resultado

            }

        }

    }
    suspend fun guardarPersona(rut:String, nombre:String, edad:Int): Long {
        delay(5000)
        var p1= Persona(0, rut, nombre, edad)
        return database.personaDAO.insertar(p1)
    }
}