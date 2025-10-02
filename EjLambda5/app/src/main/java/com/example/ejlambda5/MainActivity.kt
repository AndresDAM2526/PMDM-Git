package com.example.ejlambda5

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejlambda5.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var miBinding: ActivityMainBinding

    var miArray= Array<Int>(10,{ Random.nextInt(1,100) })
    //Creo el Array resultado
    lateinit var miArray_resultado: Array<Int>

    var es_primo:(Int) -> Boolean={ num:Int->
        var primo=true
        var divisor=2
        //Recorremos todos los valores entre 2 y num/2
        while(primo && divisor<(num/2)){
            //Comprobamos si el número es divisible entre el divisor
            primo=(num%divisor!=0)
            //Pasamos al siguiente valor de divisor
            divisor++
        }
        primo
    }

    val es_magico:(Int)->Boolean={ num:Int ->
        var magico=true
        var cubo=Math.pow(num.toDouble(), 3.toDouble())
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
        magico=(sumDigitos==num.toInt())
        magico
    }
    var es_capicua:(Int)-> Boolean={ num:Int->
        var capicua=false
        //Convierto el número a String y le doy la vuelta
        var numeroAlReves=num as String
        numeroAlReves=numeroAlReves.reversed()
        capicua=(numeroAlReves==num.toString())
        capicua

    }

    fun filtrar_numero(listaNumeros: Array<Int>,func:(Int)-> Boolean): Array<Int>{
        //Declaro una lista vacia que va a almacenar
        //los valores de lista_nuemros que cumplen
        //el filtro fun
        var listaResultados=mutableListOf<Int>()
        /*
        Recorro la lista de numeros, es decir, el primer parámetro
        y filtro segunda la función func, los valores que
        cumplen la condición definida en esa función fun
         */
        for(elementos in listaNumeros){
            /*
            Añado a listaResultado s el elemento segun la función func es true
             */
            if(func(elementos)) {
                listaResultados.add(elementos)
            }
        }
        return listaResultados.toTypedArray()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Instancio el objeto binding
        miBinding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Vinculo la vista con el objeto miBinding
        setContentView(miBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Llamamos a la función que inicializa los componentes visuales
        inicializarComponentes()
    }

    private fun inicializarComponentes(){
        miBinding.etiquetaArrayInicial.text=miArray.joinToString()
        //Asigno funcionalidad al boton
        miBinding.botonFiltrar.setOnClickListener {
            //Averiguar el radioButton seleccionado
            when(miBinding.radioGroup.checkedRadioButtonId){
                R.id.radioButtonPrimos-> {
                    Log.i("Mensaje","Filtro_primos")
                    this.miArray_resultado=filtrar_numero(miArray,es_primo)
                }
                R.id.radioButtonMagicos->{
                    Log.i("Mensaje","Filtros-magicos")
                    this.miArray_resultado=filtrar_numero(miArray,es_magico)
                }
                R.id.radioButtonCapicuas->{
                    Log.i("Mensaje","Filtro_capicua")
                    this.miArray_resultado=filtrar_numero(miArray,es_capicua)
                }
            }
            //Mostrar el resultado
            miBinding.etiquetaArrayResultado.text=miArray_resultado.joinToString()
        }
    }
}