package com.example.ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicios.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var miBinding: ActivityMainBinding
    private lateinit var miBoton: Button
    private lateinit var cadena1: EditText
    private lateinit var cadena2: EditText

    private fun anagrama(cad1:String,cad2:String): Boolean{
        var resultado: Boolean=false
        if(cad1.length!=cad2.length){
            return resultado
        }else{
            for(i in 0..<cad1.length){
                if(cad1.get(i)!=cad2.get((cad2.length-1)-i)){
                    return resultado
                }
            }
        }
        resultado=true
        return resultado
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        miBinding= ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(miBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarComponentes()
    }

    private fun inicializarComponentes(){
        this.miBoton=findViewById<Button>(R.id.miBoton)
        this.cadena1=findViewById<EditText>(R.id.cadena1)
        this.cadena2=findViewById<EditText>(R.id.cadena2)
        miBinding.miBoton.setOnClickListener {
            var resultado=anagrama(miBinding.cadena1.text.toString(),miBinding.cadena2.text.toString())
            if(resultado){
                Toast.makeText(this,"Anagrama", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"No son anagramas", Toast.LENGTH_LONG).show()
            }
        }
    }
}