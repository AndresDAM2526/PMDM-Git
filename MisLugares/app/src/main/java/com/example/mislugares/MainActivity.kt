package com.example.mislugares

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mislugares.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var miBinding: ActivityMainBinding

    private lateinit var tvTitulo: TextView

    private lateinit var btMostrarLugares: Button
    private lateinit var btPreferencias: Button
    private lateinit var btAcercaDe: Button
    private lateinit var btSalir: Button

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
        iniciarlizarComponentes()
    }

    fun iniciarlizarComponentes(){
        this.tvTitulo=findViewById<TextView>(R.id.tvTitulo)
        this.btMostrarLugares=findViewById<Button>(R.id.btMostrarLugares)
        this.btPreferencias=findViewById<Button>(R.id.btPreferencias)
        this.btAcercaDe=findViewById<Button>(R.id.btAcercaDe)
        this.btSalir=findViewById<Button>(R.id.btSalir)
    }
}