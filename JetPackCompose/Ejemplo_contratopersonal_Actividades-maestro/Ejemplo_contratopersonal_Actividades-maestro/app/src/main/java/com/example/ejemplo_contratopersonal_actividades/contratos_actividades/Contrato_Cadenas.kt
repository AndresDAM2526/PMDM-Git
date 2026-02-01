package com.example.ejemplo_contratopersonal_actividades.contratos_actividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
//Defino la clase Contrato_Cadenas parametrizada de un tipo que hereda de Activity porque le voy a pasar la clase actividad, como entrada tiene un String y salida un String
class  Contrato_Cadenas<T:Activity>(val clase_actividad:Class<T>): ActivityResultContract<String, String>() {
    override fun createIntent(context: Context, input: String): Intent {
        //Aqui tendré que retornar el intent que utilizará alguien para realizar la acción
        //que tenga asociada ese intent
        //A ese intent le pasaré el String que yo quiera (input)
        //El contexto le viene del método que directamente invoca a createIntent, que en
        //este caso es launch (método de la clase
       val miintent=Intent(context,clase_actividad)
        //al intent le paso el dato que quiero enviar a la actividad
        miintent.putExtra("dato",input)
        return miintent
    }

    //En este método se devuelve el valor que nos retorna el Activity B
    //Si defino esta función, basicamente tendré que controlar el valor
    //del resultCode,  que será fundamentalmente
    // Activity.RESULT_OK o  Activity.RESULT_CANCELED (eso lo defino en e
    //la actividad B), obtengo el dato que viene en el intent del 2º parametro
    // y retorno ese dato como String que es lo que obtendrá el parametro del
    // la función callback que se pasa como 2º parametro a la función
    //registerForActivityResult
    override fun parseResult(resultCode: Int, intent: Intent?): String {
      if(resultCode==Activity.RESULT_OK && intent!=null)
      {
          return intent.extras?.getString("dato_retorno").toString()
      }
        else
            return ""
    }

}
