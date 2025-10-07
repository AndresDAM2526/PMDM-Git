package com.example.ejemplopoo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
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
            when(miSpinner.selectedItem){
                "Camion"->{
                    var camion=Camion("1","2",2020,120,1200.0)
                    simularConduccion(camion)
                }
                "Motocicleta"->{
                    var moto= Motocicleta("2","34",2025,120,)
                    Toast.makeText(this,moto.arrancar().toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(this,moto.acelerar().toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(this,moto.detener().toString(), Toast.LENGTH_LONG).show()
                }
                "Auto"->{
                    var auto= Auto("3","43",2025,120,5)
                    Toast.makeText(this,auto.arrancar().toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(this,auto.acelerar().toString(), Toast.LENGTH_LONG).show()
                    Toast.makeText(this,auto.detener().toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}