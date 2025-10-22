package com.example.ejerciciopoo

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import com.example.ejerciciopoo.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var miBinding: ActivityMainBinding

    private lateinit var btGuardar: Button

    private lateinit var tvLogin: TextView
    private lateinit var tvPass: TextView
    private lateinit var tvFechNac: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvTipoUsuario: TextView
    private lateinit var tvDescripcion: TextView

    private lateinit var tvMembresia: TextView

    private lateinit var tvArea: TextView

    private lateinit var etLogin: EditText
    private lateinit var etPass: EditText
    private lateinit var etEmail: EditText
    private lateinit var etDescripcion: EditText

    private lateinit var etMembresia: EditText

    private lateinit var etArea: EditText

    private var tipoUsuario:List<String> = listOf("Usuario Normal","Administrador")

    private var usuarios= mutableListOf<Usuario>()

    private lateinit var spinner: Spinner
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        miBinding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(miBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarlizarComponentes()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun iniciarlizarComponentes(){
        this.btGuardar=findViewById<Button>(R.id.btGuardar)
        this.tvLogin=findViewById<TextView>(R.id.tvLogin)
        this.etLogin=findViewById<EditText>(R.id.etLogin)
        this.tvPass=findViewById<TextView>(R.id.tvPass)
        this.etPass=findViewById<EditText>(R.id.etPass)
        this.tvEmail=findViewById<TextView>(R.id.tvEmail)
        this.etEmail=findViewById<EditText>(R.id.etEmail)
        this.tvFechNac=findViewById<TextView>(R.id.tvFechNac)
        this.tvTipoUsuario=findViewById<TextView>(R.id.tvTipoUsuario)
        this.spinner=findViewById<Spinner>(R.id.spinner)
        this.tvDescripcion=findViewById<TextView>(R.id.tvDescripcion)
        this.etDescripcion=findViewById<EditText>(R.id.etDescripcion)
        this.tvMembresia=findViewById<TextView>(R.id.tvMembresia)
        this.etMembresia=findViewById<EditText>(R.id.etMembresia)
        this.tvArea=findViewById<TextView>(R.id.tvArea)
        this.etArea=findViewById<EditText>(R.id.etArea)

        this.tvMembresia.visibility= View.GONE
        this.etMembresia.visibility=View.GONE
        this.tvArea.visibility=View.GONE
        this.etArea.visibility= View.GONE



        var adaptador= ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tipoUsuario)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinner.adapter=adaptador

        this.spinner.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var seleccionado=parent?.getItemAtPosition(position).toString()
                if(seleccionado=="Usuario Normal"){
                    tvMembresia.visibility= View.VISIBLE
                    etMembresia.visibility= View.VISIBLE
                    tvArea.visibility= View.GONE
                    etArea.visibility= View.GONE
                }
                if(seleccionado=="Administrador"){
                    tvArea.visibility= View.VISIBLE
                    etArea.visibility= View.VISIBLE
                    tvMembresia.visibility= View.GONE
                    etMembresia.visibility= View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        this.btGuardar.setOnClickListener {
            var login:String=this.etLogin.text.toString()
            var pass:String=this.etPass.text.toString()
            var email: String=this.etEmail.text.toString()
            var tipoUsuario:String=this.spinner.selectedItem.toString()
            var descripcion:String=this.etDescripcion.text.toString()
            if(this.spinner.selectedItem.toString()=="Usuario Normal"){
                var membresia=(this.etMembresia.text.toString()).toInt()
                var us= UsuarioNormal(login,pass, LocalDate.now(),email,membresia)
                usuarios.add(us)
            }else if (this.spinner.selectedItem.toString()=="Administrador"){
                var area=this.etArea.text.toString()
                var admin= Administrador(login,pass,LocalDate.now(),email,area)
                usuarios.add(admin)
            }

        }

    }
    
}