package com.example.examenprimerparcialandrescorreagarcia

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.examenprimerparcialandrescorreagarcia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var miBinding: ActivityMainBinding

    //Defino un mutableList de Lista_compra para poder almacenar
    //todas las listas de la compra
    val mis_listas_compra = mutableListOf<Lista_Compra>()
    val mi_lista_compra_actual: Lista_Compra?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        miBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(miBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar: Toolbar = miBinding.toolbar
        toolbar.setTitle("LISTA DE LA COMPRA")
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white, theme))
        setSupportActionBar(toolbar)

        inicializarComponentes()

        /*
        //Creo un objeto de la clase lista_Compra
        var milista_compra = Lista_Compra(Calendar.getInstance().time)
        milista_compra.Agregar_Producto(Producto_cesta("Galletas", TipoProducto.COMIDA, 2.0))
        milista_compra.Agregar_Producto(Producto_cesta("Gel", TipoProducto.LIMPIEZA, 2.5))
        milista_compra.Agregar_Producto(Producto_cesta("Refrescos", TipoProducto.BEBIDA, 5.0))
        milista_compra.Agregar_Producto(Producto_cesta("Pescado", TipoProducto.COMIDA, 12.0))

        //Filtro los productos con precio mayor que 10
        milista_compra.filtrar_productos { it.precio >= 10 }

        //Filtro productos cuyo primer caracter del nombre del producto es a
        milista_compra.filtrar_productos {
            it.nombre.get(0) == 'A'
            /*it.nombre.startsWith("A")*/
        }

        //Filtrar productos por tipo producto
        milista_compra.filtrar_productos { it.tipo == TipoProducto.COMIDA }
         */
    }

    private fun inicializarComponentes() {
        //DatePickerDialog
        //Cuando se pulse encima del campo de fecha de compra debe desplegarse el DatePickerDialong
        miBinding.etFechaCompra.setOnClickListener {
            //Abrimos el DatePickerDialog
            //Obtenemos la fecha actual
            val calendario = Calendar.getInstance()
            //Instancio un objeto DatePickerDialog
            val miDatePicker = DatePickerDialog(
                this,
                { vista, dia, mes, anio ->
                    miBinding.etFechaCompra.setText("$dia-${mes + 1}-$anio")
                    //Aqui voy a gestionar si existe una lista de la compra con la fecha selecionada

                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            )
            //Muestro el DatePickerDialog
            miDatePicker.show()
        }


        //Spinner
        var adaptadorSpinner = ArrayAdapter<TipoProducto>(
            this,
            android.R.layout.simple_spinner_item,
            TipoProducto.values()
        )
        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        miBinding.spinnerTipoProducto.adapter = adaptadorSpinner

        //Deshabilitar los botones de recorrido y añadir producto
        habilitar_botones(false)
        miBinding.etNombreProducto.addTextChangedListener {
            //Compruebo si los valores de los componentes están vacios
            if ((!miBinding.etNombreProducto.text.isEmpty()) && (!miBinding.etFechaCompra.text.isEmpty()) && (!miBinding.etImporte.text.isEmpty())) {
                //Habilito los botones de nuevo
                habilitar_botones(true)
            } else {
                habilitar_botones(false)
            }
        }
    }

    fun habilitar_botones(estado: Boolean) {
        miBinding.btAvanzar.isEnabled = estado
        miBinding.btRetroceder.isEnabled = estado
        miBinding.btAnadirProducto.isEnabled = estado
    }


}

