package com.example.examen2parcial25_26.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.examen2parcial25_26.data.ProveedorContacto
import com.example.examen2parcial25_26.modelo.Contacto

class ContactosViewModel: ViewModel() {

    var contactos = mutableStateListOf<Contacto>()
        private set //para que no pueda modificar desde fuera

    //Inicializo contactos
    init {
        contactos.addAll(ProveedorContacto.contactos)
    }

    fun guardarContactos(){
        ProveedorContacto.grabarContactosAJson(contactos)
    }

    fun añadirContacto(contacto: Contacto) {
        contactos.add(contacto)
    }

    fun actualizarContacto(contactoOriginal: Contacto,contactoactualizado: Contacto)
    {
        //voy a buscar el heroeoriginal
        val indice=contactos.indexOf(contactoOriginal)
        if(indice!=-1)
        {
            //Encontrado, actualizo la lista
            contactos[indice]=contactoactualizado
        }
    }
}