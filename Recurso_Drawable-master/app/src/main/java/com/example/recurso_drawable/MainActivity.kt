package com.example.recurso_drawable

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var boton: Button
    private var activo = true
    private lateinit var imagen: ImageView
    private lateinit var handler: Handler
    private lateinit var handlerColoresBoton: Handler
    private lateinit var mirunable: Runnable
    private lateinit var mirunableColores: Runnable


    //Manera de declarar un array de imágenes
    /*arrayOf(
    R.drawable.wp4470362_1440x2880_wallpapers,
    R.drawable.wp4470363_1440x2880_wallpapers,
    R.drawable.wp4470366_1440x2880_wallpapers,
    R.drawable.androidparty
)*/
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
             insets
         }*/
        var arrayCombinado = resources.obtainTypedArray(R.array.combinacion)
        var arrayColores = resources.obtainTypedArray(R.array.colores)
        //Inicializo el array de imágenes
        var arrayImages = resources.obtainTypedArray(R.array.fondos_pantalla)
        var imagen = findViewById<ImageView>(R.id.imageView)
        boton = findViewById<Button>(R.id.boton);
        boton.setOnClickListener {
            boton.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
        }


        var miArrayEnteros = resources.getIntArray(R.array.array_enteros)
        //miarray (2,3,4,5,6)

        var miArrayStrings = resources.getStringArray(R.array.array_strings)
        Toast.makeText(this, miArrayEnteros.toString(), Toast.LENGTH_LONG).show()


        //Escuchador para la imagen
        imagen.setOnClickListener {
            mirunable.let {
                if (activo)

                //Elimino las tareas pendientes del handler, por lo que para el runable
                    handler.removeCallbacks(it)
                else
                //Comienzan de nuevo el salvapantallas
                    handler.post(mirunable)
                this.activo = !this.activo
            }

        }
        //Instancio el handler
        handler = Handler(Looper.getMainLooper())
        handlerColoresBoton = Handler(Looper.getMainLooper())


        mirunableColores = object : Runnable {
            override fun run() {
                boton.setBackgroundColor(arrayColores.getColor(i,R.color.pink.toInt()))
                i = ++i % arrayColores.length()
                handlerColoresBoton.postDelayed(this, 1000)
            }

        }
        handlerColoresBoton.post(mirunableColores)

        //Defino un runable
        mirunable = object : Runnable {
            //Metodo que se ejecuta
            override fun run() {
                //Establezco la imagen
                /*
                imagen.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        arrayImages.getDrawable(i)
                    )
                )*/
                imagen.setImageDrawable(arrayImages.getDrawable(i))

                //Otra forma de establecer la imagen, pero más lento
                //  imagen.setImageResource(arrayImages[i])
                //Paso a la siguiente imagen
                i = ++i % arrayImages.length()

                //Definir cada cuanto tiempo se ejecuta este manejador
                handler.postDelayed(this, 3000)
            }

        }
        //Ejecutar la tarea inmediatamente
        handler.post(mirunable)


    }
}