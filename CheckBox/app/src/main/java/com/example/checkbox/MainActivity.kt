package com.example.checkbox

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.checkbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var miBinding: ActivityMainBinding

    lateinit var cbLunes: CheckBox
    lateinit var cbMartes: CheckBox
    lateinit var cbMiercoles: CheckBox
    lateinit var cbJueves: CheckBox
    lateinit var cbViernes: CheckBox
    lateinit var cbSabado: CheckBox
    lateinit var cbDomingo: CheckBox

    lateinit var tvOpcion: TextView

    lateinit var btEnviar: Button
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        miBinding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(miBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarComponentes()

    }

    fun inicializarComponentes() {
        this.cbLunes = findViewById<CheckBox>(R.id.cbLunes)
        this.cbMartes = findViewById<CheckBox>(R.id.cbMartes)
        this.cbMiercoles = findViewById<CheckBox>(R.id.cbMiercoles)
        this.cbJueves = findViewById<CheckBox>(R.id.cbJueves)
        this.cbViernes = findViewById<CheckBox>(R.id.cbViernes)
        this.cbSabado = findViewById<CheckBox>(R.id.cbSabado)
        this.cbDomingo = findViewById<CheckBox>(R.id.cbDomingo)

        this.tvOpcion = findViewById<TextView>(R.id.tvOpcion)
        this.btEnviar = findViewById<Button>(R.id.btEnviar)

        btEnviar.setOnClickListener {
            tvOpcion.setText("")
            if (cbLunes.isChecked) {
                tvOpcion.setText("${tvOpcion.text} ${cbLunes.text}")
            } else if (cbMartes.isChecked) {
                tvOpcion.setText("${tvOpcion.text} ${cbMartes.text}")
            } else if (cbMiercoles.isChecked) {
                tvOpcion.setText("${tvOpcion.text} ${cbMiercoles.text}")
            } else if (cbJueves.isChecked) {
                tvOpcion.setText("${tvOpcion.text} ${cbJueves.text}")
            } else if (cbViernes.isChecked) {
                tvOpcion.setText("${tvOpcion.text} ${cbViernes.text}")
            } else if (cbSabado.isChecked) {
                tvOpcion.setText("${tvOpcion.text} ${cbSabado.text}")
            } else {
                tvOpcion.setText("${tvOpcion.text} ${cbDomingo.text}")
            }
        }


    }
}