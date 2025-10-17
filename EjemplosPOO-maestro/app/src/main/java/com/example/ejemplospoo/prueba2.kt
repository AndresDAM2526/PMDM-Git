package com.example.ejemplospoo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class prueba2 : AppCompatActivity() {

    private lateinit var btGuardar: Button
    private lateinit var tvMostrar: TextView
    private lateinit var etTexto: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_prueba2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btGuardar = findViewById<Button>(R.id.btGuardar)
        tvMostrar = findViewById<TextView>(R.id.tvMostrar)
        etTexto = findViewById<EditText>(R.id.etTexto)

        btGuardar.setOnClickListener {
            mostrarDialogo(
                "Guardar",
                "¿Desea guardar el texto?",
                {
                    tvMostrar.text=etTexto.text
                    etTexto.text.clear()
                    it.dismiss()
                },
                {
                    it.dismiss()
                    etTexto.text.clear()
                })
        }
    }
}