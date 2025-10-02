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
        var num= Math.pow(a.toDouble(),3.0)
        var numString=num.toString()
        var acumulado: Int=0
        for (i in numString){
            if(i.isDigit()){
                acumulado+=i.toString().toInt()
            }

        }
        num.toInt() ==acumulado
    }


    //Solución dada por Roberto
    val es_magico:(Int)->Boolean={ num:Int ->
        var magico=true
        var cubo=Math.pow(num as Double, 3 as Double)
        var sumDigitos=0
        var digito=cubo as Int %10
        var cociente: Int=cubo as Int
        //VOy separando todos los digitos del cubo
        while (cociente!=0) {
            digito=cociente%10
            //preparo el bucle para la siguiente iteración
            cociente=cociente/10
            sumDigitos+=digito
        }
        magico=(sumDigitos==num as Int)
        magico




        magico
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
        btNumMagico.setOnClickListener { var resultado=magico(editTextNum.text.toString().toInt())
            if(resultado){
                Toast.makeText(this,"El numero es mágico", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"El numero no es mágico", Toast.LENGTH_LONG).show()
            } }

    }
}