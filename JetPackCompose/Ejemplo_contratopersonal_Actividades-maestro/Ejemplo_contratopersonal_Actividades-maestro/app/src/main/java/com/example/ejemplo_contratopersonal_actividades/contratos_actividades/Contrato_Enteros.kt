package com.example.ejemplo_contratopersonal_actividades.contratos_actividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class Contrato_Enteros(val dato:Integer):ActivityResultContract<Class<*>,Int>(){
    override fun createIntent(context: Context, input: Class<*>): Intent {
        val miintent=Intent(context,input)
        miintent.putExtra("dato",dato)
        return miintent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int {
        if(resultCode==Activity.RESULT_OK)
        {
            return intent?.extras?.getInt("dato_vuelta") ?: 0
        }
        else
            return 0
    }
}
//Ejercicio.- Crea una actividad que responda a este envio de datos

