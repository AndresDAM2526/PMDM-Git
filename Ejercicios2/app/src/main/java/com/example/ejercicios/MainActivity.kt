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
    //val es para declarar una constate, una vez que se declara luego ya no se puede cambiar
    //val a=2

    private lateinit var miBinding: ActivityMainBinding
    private lateinit var miBoton: Button
    private lateinit var cadena1EditText: EditText
    private lateinit var cadena2EditText: EditText

    private fun anagrama(cad1:String,cad2:String): Boolean{
        var resultado: Boolean=false
        if(cad1.length!=cad2.length){
            return resultado
        }else{
            var cad1F=cad1.lowercase()
            var cad2F=cad2.lowercase()
            for(i in 0..<cad1.length){
                if(cad1F.get(i)!=cad2F.get((cad2F.length-1)-i)){
                    return resultado
                }
            }
        }
        resultado=true
        return resultado
    }

    //Solución dada por Roberto
    private fun anagrama2(cad1: String,cad2: String): Boolean{
        //cad1 es la cadena que recorro
        //declaro una cadena resultado que inicialmente es cad2, es la que se va a ir acortando
        var cadena_resultado=cad2
        //declaro otra variable que me diga si las cadenas son anagramas o no
        var es_anagrama=true
        var i=0
        //Recorro la cadena buscando el caracter en la cadena resultado
        //si existe el caracter lo elimino de la cadena_resultado
        //si no existe salgo del bucle
        while (es_anagrama && (i<cad1.length)){
            //Comprobar si el caracter de la posición de cad1 esta en cadena_resultado
            if(cadena_resultado.contains(cad1.get(i))){
                cadena_resultado=cadena_resultado.replaceFirst(cad1.get(i).toString(),"",true)
            }else{
                es_anagrama=false
            }
            i++
        }
        return es_anagrama
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
        this.cadena1EditText=findViewById<EditText>(R.id.cadena1)
        this.cadena2EditText=findViewById<EditText>(R.id.cadena2)
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