package com.example.examenprimerparcialandrescorreagarcia

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examenprimerparcialandrescorreagarcia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var miBinding: ActivityMainBinding

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

        iniciarlizarSpinner()
    }

    private fun iniciarlizarSpinner() {
        var adaptadorSpinner = ArrayAdapter<TipoProducto>(
            this,
            android.R.layout.simple_spinner_item,
            TipoProducto.values()
        )
        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        miBinding.spinnerTipoProducto.adapter = adaptadorSpinner

    }


}

