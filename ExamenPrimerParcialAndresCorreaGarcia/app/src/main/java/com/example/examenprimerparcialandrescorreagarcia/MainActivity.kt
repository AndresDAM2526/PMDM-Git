package com.example.examenprimerparcialandrescorreagarcia

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.examenprimerparcialandrescorreagarcia.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var miBinding: ActivityMainBinding

    //Defino un mutableList de Lista_compra para poder almacenar
    //todas las listas de la compra
    val mis_listas_compra = mutableListOf<Lista_Compra>()
    var mi_lista_compra_actual: Lista_Compra? = null
    var indice_producto = 0

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

    @SuppressLint("SetTextI18n")
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
                    var fecha_compra = SimpleDateFormat("dd-MM-yyyy").parse("$dia-${mes + 1}-$anio")
                    mi_lista_compra_actual = mis_listas_compra.find { it.fecha == fecha_compra }
                    if (mi_lista_compra_actual != null) {
                        //Existe una lista de la compra con esa fecha
                        val builder = AlertDialog.Builder(this)
                        builder.run {
                            setMessage("Ya hay una lista de la compra con esa fecha")
                            setTitle("LISTA COMPRA")
                            setPositiveButton("Aeptar") { _, _ ->
                            }
                            create().show()
                        }
                    } else {
                        //No existe la lista de la compra,la creo
                        //Instancio la nueva lista de la compra
                        mi_lista_compra_actual = Lista_Compra(fecha_compra)
                        //la añado a las listas
                        mis_listas_compra.add(mi_lista_compra_actual!!)
                    }

                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            )
            //Muestro el DatePickerDialog
            miDatePicker.show()
            //Calculamos el importe y lo mostramos
            miBinding.tvImporte.text = "IMPORTE TOTAL ${mi_lista_compra_actual!!.calcularTotal()}"
            //Habilito el botón avanzar si hay más productos
            miBinding.btAvanzar.isEnabled =
                (mi_lista_compra_actual!!.obtener_productos_cesta().size > 1)
            //Si existe un producto en esa lista de la compra muestro el producto
            if (mi_lista_compra_actual!!.obtener_productos_cesta().size >= 1) {
                val producto_cesta = mi_lista_compra_actual!!.obtener_productos_cesta().get(0)
                miBinding.spinnerTipoProducto.setSelection(producto_cesta.tipo.ordinal)
                miBinding.etNombreProducto.setText(producto_cesta.nombre)
                miBinding.etImporte.setText(producto_cesta.precio.toString())
            }
            //Defino el código al pulsar el botón añadir
            miBinding.btAnadirProducto.setOnClickListener {
                //Añadir el producto a la lista de la compra actual
                //Creo un producto
                var miProducto = Producto_cesta(
                    miBinding.etNombreProducto.text.toString(),
                    miBinding.spinnerTipoProducto.selectedItem as TipoProducto,
                    miBinding.etImporte.text.toString().toDouble()
                )
                mi_lista_compra_actual?.let {
                    it.Agregar_Producto(miProducto)
                }
                //Vacio los campos de texto
                miBinding.etNombreProducto.setText("")
                miBinding.etImporte.setText("")
                //Recalculo el importe total
                miBinding.tvImporte.text =
                    "IMPORTE TOTAL ${mi_lista_compra_actual!!.calcularTotal()}"
            }
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
        miBinding.btAvanzar.setOnClickListener {
            //Si no estoy en el final de la lista de productos de esa lista de la compra
            if (indice_producto <= mi_lista_compra_actual!!.obtener_productos_cesta().size) {
                //Muestro los datos de ese producto
                var producto = mi_lista_compra_actual?.let {
                    it.obtener_productos_cesta().get(indice_producto)
                }
                miBinding.etNombreProducto.setText(producto?.nombre)
                miBinding.etImporte.setText(producto?.precio.toString())
                miBinding.spinnerTipoProducto.setSelection(producto?.tipo?.ordinal ?: 0)
                indice_producto++
                //Habilito el botón de retroceso
                miBinding.btRetroceder.isEnabled = indice_producto > 0
            } else {
                //Deshabilitar el botón
                it.isEnabled = false
            }
        }

        miBinding.btRetroceder.setOnClickListener {
            //Si no estoy al principio de la lista de productos de la lista de productos actual
            if (indice_producto > 0) {
                var producto = mi_lista_compra_actual?.let {
                    it.obtener_productos_cesta().get(indice_producto)
                }
                miBinding.etNombreProducto.setText(producto?.nombre)
                miBinding.etImporte.setText(producto?.precio.toString())
                miBinding.spinnerTipoProducto.setSelection(producto?.tipo?.ordinal ?: 0)
                indice_producto--
            } else {
                it.isEnabled = false
            }
        }

        miBinding.switchTipoProducto.setOnClickListener {
            var lista_productos: MutableList<Producto_cesta>?
            lista_productos=null
            //Solo filtraré si hay productos que filtrar
            if (mi_lista_compra_actual!!.obtener_productos_cesta().size > 1) {
                if (it.isActivated) {
                    //Filtrar los productos
                    lista_productos = mi_lista_compra_actual?.let {
                        it.filtrar_productos { it.tipo == miBinding.spinnerTipoProducto.selectedItem } as MutableList
                    }
                }

                //Actualizo el importe total de la lista de productos
                var importe = lista_productos?.let {
                    it.sumOf { it.precio }
                }
                miBinding.tvImporte.text =
                    "IMPORTE TOTAL ${mi_lista_compra_actual!!.calcularTotal()}"
            }

        }

    }

    fun habilitar_botones(estado: Boolean) {
        miBinding.btAvanzar.isEnabled = estado
        miBinding.btRetroceder.isEnabled = estado
        miBinding.btAnadirProducto.isEnabled = estado
    }


}

