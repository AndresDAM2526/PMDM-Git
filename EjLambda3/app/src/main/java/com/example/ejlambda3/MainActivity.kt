package com.example.ejlambda3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btCapicua: Button
    private lateinit var etNum: EditText

    var capicua:(Int)-> Boolean={
        a: Int->
        var cadena=a.toString()
        var cadenaInversa=""
        for (i in cadena.length-1 downTo 0){
            cadenaInversa+=cadena[i]
        }

        cadena==cadenaInversa

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
        this.btCapicua=findViewById<Button>(R.id.btCapicua)
        this.etNum=findViewById<EditText>(R.id.etNum)
        btCapicua.setOnClickListener {
            var resultado=capicua(etNum.text.toString().toInt())
            if(resultado){
                Toast.makeText(this,"Capicua", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"No capicua", Toast.LENGTH_LONG).show()
            }
        }
    }
}