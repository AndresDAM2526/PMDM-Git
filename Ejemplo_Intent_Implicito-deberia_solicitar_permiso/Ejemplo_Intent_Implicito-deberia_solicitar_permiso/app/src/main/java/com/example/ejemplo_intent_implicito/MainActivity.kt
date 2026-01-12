package com.example.ejemplo_intent_implicito

import android.Manifest
import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var boton_llamar:Button
    lateinit var intent_llamar:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        InitComponentes()
    }

    private fun InitComponentes() {
        this.boton_llamar=findViewById(R.id.button)
        boton_llamar.setOnClickListener {
            intent_llamar=Intent(Intent.ACTION_CALL)
            intent_llamar.setData(Uri.parse("tel:983454554"))
            // COMO VERIFICAR SI TU APP TIENE PERMISOS
            // https://developer.android.com/training/permissions/requesting?hl=es-419
            //Ejercicio.- Averigue como mostrar selector de permisos

            //Los permisos se definen como constantes en codigo
            // desde la clase Manifest.permission.<PERMISO QUE SEA>


            //Función ContextCompat.checkSelfPermission , averiguar permiso
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            {
                Log.i("INFO","No tengo el permiso concedido")
                //En la rama de git deberia_solicitar_permiso antes de mostrar el dialogo para solicitar
                //al usuario, compruebo si es un permiso en el que
                //debo preguntarle al usuario para explicarle porque
                //hay que conceder el permiso
                if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE))
                    {
                    Log.i("INFO","Dentro de shouldShow")
                    //Suele devolver true si anteriormente hemos denegado el permiso, pero si ya hay 2 veces
                        //que hemos denegado el permiso devuelve false
                    //Muestro un AlertDialog
                    var alertDialog=AlertDialog.Builder(this)
                        alertDialog.apply {
                            setTitle("PERMISO DE LLAMADA NECESARIO")
                            setMessage("Si no concedes el permiso la app no funciona")
                            setPositiveButton(android.R.string.ok,DialogInterface.OnClickListener { dialog, which ->
                                //Si el usuario pulsa el boton ok, entonces solicito el permiso
                                //Muestro dialogo para solicitar el permiso al ususario
                                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),1)
                            })
                        }
                        //Muestro el alertDialog
                        alertDialog.create().show()

                    }
                else
                {
                    Log.i("INFO","No tengo el permiso, pero requiero el permiso")
                    requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),1)
                }


            }
            else{
                startActivity(intent_llamar)
            }



            //Funcion requestPermissions, muestra dialogo que pregunta al
                // usuario si quiere conceder ese permiso

            //Implementación funcion onRequestPermissionsResult,
               //En la que se ejecuta el codigo cuando el usuario responda


/*
                val mirotador=ObjectAnimator.ofFloat(imagen,"rotacion",0f,560f)
                mirotador.duration=1000
                mirotador.start()
*/


            }

        }
//Se invoca una vez
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==1)
        {//Comprobamos si venimos de la petición de solicitud de permisos
            //1
            Log.i("INFO","Dentro de onRequesPermission")
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                //Que hacemos?
                startActivity(intent_llamar)
            }

        }

    }

    }





