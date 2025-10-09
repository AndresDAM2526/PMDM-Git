package com.example.ejemplopoo

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btSimular: Button
    lateinit var miSpinner: Spinner
    var tiposVehiculos=arrayOf("Camion","Auto","Motocicleta")

    fun simularConduccion(vehiculo: Conducible){
        vehiculo.arrancar()
        if(vehiculo is Vehiculo){
            vehiculo.acelerar()
        }
        vehiculo.detener()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        miSpinner=findViewById<Spinner>(R.id.spinner)
        configurarSpinner()
    }

    fun configurarSpinner(){
        var miAdaptador= ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposVehiculos)
        findViewById<Button>(R.id.btSimular)
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        miSpinner.adapter=miAdaptador
        findViewById<Button>(R.id.btSimular).setOnClickListener {
            var miVehiculo: Vehiculo?=null
            when(miSpinner.selectedItem.toString()){
                "Camion"->//Instancio el objeto de la clase camión
                    miVehiculo=Camion("1","2",2020,120,1200.0)
                "Motocicleta"-> //Instancio el objeto de la clase motocicleta
                    miVehiculo= Motocicleta("Suzuki","530",2025,120,)

                "Auto" -> //Instancio el objeto de la clave vehiculo
                     miVehiculo= Auto("3","43",2025,120,5)


            }
            //Mostrar em un AlertDialog todos los mensajes que se
            //generan con la invocación de los métocoos arrancar, acelerar y
            //detener

            var builderDialog= AlertDialog.Builder(this)
            builderDialog.apply {
                setTitle(miSpinner.selectedItem.toString())
                setMessage("${miVehiculo?.arrancar()} \n${miVehiculo?.acelerar()}\n${miVehiculo?.detener()}")
                setPositiveButton("Aceptar",{
                    d,which ->
                    if(which== DialogInterface.BUTTON_POSITIVE) {
                        d.dismiss()
                    }
                })
            }
            //Muestro el AlertDialog
            builderDialog.create().show()
        }


    }
}