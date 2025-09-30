package com.example.ejlambda2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNum: EditText
    private lateinit var btNumMagico: Button

    var magico:(Int)-> Boolean={
        a ->

        var cadenaNum=a.toString()
        var acumulado: Int=0
        for(i in cadenaNum){
            acumulado+=i.toString().toInt()
        }
        acumulado==a
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
        this.editTextNum=findViewById<EditText>(R.id.EditTextNum)
        this.btNumMagico=findViewById<Button>(R.id.btNumMagico)
        var resultado=magico(editTextNum.text.toString().toInt())
        if(resultado){
            Toast.makeText(this,"El numero es mágico", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"El numero no es mágico", Toast.LENGTH_LONG).show()
        }
    }
}