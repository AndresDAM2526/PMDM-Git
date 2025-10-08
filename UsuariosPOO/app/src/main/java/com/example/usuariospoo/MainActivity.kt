package com.example.usuariospoo

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.example.usuariospoo.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var miBinding: ActivityMainBinding
    private lateinit var usuarios: ArrayList<Usuario>
    var tipoUsuario=arrayOf("Usuario normal","Administrador")
    private lateinit var etLogin: EditText
    private lateinit var etPass: EditText
    private lateinit var etFechaNac: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMembresia: EditText
    private lateinit var etArea: EditText

    private lateinit var btAnadir: Button

    private lateinit var miSpinner: Spinner
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
        this.miSpinner=findViewById<Spinner>(R.id.spinner)
        inicializarComponentes()
    }

    private fun inicializarComponentes(){
        this.etLogin=findViewById<EditText>(R.id.etLogin)
        this.etPass=findViewById<EditText>(R.id.etPass)
        this.etFechaNac=findViewById<EditText>(R.id.etFechaNac)
        this.etMembresia=findViewById<EditText>(R.id.etMembresia)
        this.etArea=findViewById<EditText>(R.id.etArea)
        this.btAnadir=findViewById<Button>(R.id.btAnadir)


        var miAdaptador= ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tipoUsuario)
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        miSpinner.adapter=miAdaptador
        when(miSpinner.selectedItem){
            "Usuario normal"->{
                miBinding.etArea.visibility= View.GONE
                var login=miBinding.etLogin.text.toString()
                var pass=miBinding.etPass.text.toString()
                var fechNac=miBinding.etFechaNac.toString()
                var email=miBinding.etEmail.toString()
                var membresia=miBinding.etMembresia.toString()
                var formato= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                var fechaFormateada=formato.parse(fechNac)
                /*var usuario= UsuarioNormal(login,pass,fechaFormateada,email,membresia.toInt())
                miBinding.btAnadir.setOnClickListener {
                    usuarios.add(usuario)
                }*/


            }
            "Adminstrador"->{
                miBinding.etMembresia.visibility= View.GONE
                var login=miBinding.etLogin.text.toString()
                var pass=miBinding.etPass.text.toString()
                var fechNac=miBinding.etFechaNac.toString()
                var email=miBinding.etEmail.toString()
                var area=miBinding.etArea.toString()
                var formato= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                var fechaFormateada=formato.parse(fechNac)
                /*var admin= Administrador(login,pass,fechaFormateada,email,area)
                miBinding.btAnadir.setOnClickListener {
                    usuarios.add(admin)
                }*/

            }
        }
    }
}