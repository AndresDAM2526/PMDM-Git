package com.example.a

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btSimular: Button
    lateinit var miSpinner: Spinner
    var tipos_vehiculos=arrayOf("Camion","Auto","Motocicleta")
    fun simularConduccion(v: Conducible){
        //Invocar arrancar
        v.arrancar();
        //Si el objeto V es un vehículo
        if(v is Vehiculo) v.acelerar()
        //Invocar a detener
        v.detener()
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
        miSpinner=findViewById<Spinner>(R.id.spinner);
        configurar_spinner()
    }

    private fun configurar_spinner(){
        //Definir adaptador que define los datos
        //que se muestran en el spinner y la apariencia que va a tener
        var miAdaptador= ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tipos_vehiculos)
        findViewById<Button>(R.id.btSimular).setOnClickListener {
            //Averiguar que elemento hay seleccionado en el spinner
            miSpinner.selectedItem

        }
        //Defino la lista que va a tener el spinner cuando se despliega
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Asigno al spinner el adaptador
        miSpinner.adapter=miAdaptador;
    }
}