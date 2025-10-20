package com.example.ejercicio2_pag67

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.letra_escrita(listener:(String)-> Unit){
    this.addTextChangedListener(object: TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            val numCaracteres=s?.length?:0
            val caracter="_".repeat(numCaracteres)
            listener(caracter)
        }

    })
}