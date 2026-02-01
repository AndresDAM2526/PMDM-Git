package com.example.ejemplo_contratopersonal_actividades.contratos_actividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

//Clase parametrizada con T, donde T debe ser una subclase de Activity
/*
class Contrato_Cadenasbis<T : Activity> : ActivityResultContract<String, String?>() {

    override fun createIntent(context: Context, input: String): Intent {
        // Creamos un Intent dinámico con la clase genérica
        return Intent(context, T::class.java).apply {
            putExtra("input_key", input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        // Devolvemos el resultado si el resultado es OK
        return if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra("result_key")
        } else {
            null
        }
    }
}

 */