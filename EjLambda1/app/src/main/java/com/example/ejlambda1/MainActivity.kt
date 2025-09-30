package com.example.ejlambda1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var cadenaEditText: EditText
    private lateinit var miBoton: Button
    var esPrimo: (Int)-> Boolean={
            a: Int->
        var contador: Int=0
        for (i in 1..a){
            if(a%i==0){
                contador++
            }
        }
        contador==2
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
        inicializarComponentes()
    }

    private fun inicializarComponentes(){
        this.cadenaEditText=findViewById<EditText>(R.id.EditTextPrimo)
        this.miBoton=findViewById<Button>(R.id.btPrimo)
        miBoton.setOnClickListener {
            var num=cadenaEditText.text.toString().toInt()
            var resultado=esPrimo(num)
            if(resultado){
                Toast.makeText(this,"El numero es primo",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"El numero no es primo",Toast.LENGTH_SHORT).show()
            }
        }
    }
}