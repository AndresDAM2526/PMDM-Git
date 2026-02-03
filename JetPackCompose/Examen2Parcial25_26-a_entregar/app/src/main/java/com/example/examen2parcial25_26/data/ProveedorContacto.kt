package com.example.examen2parcial25_26.data

import android.content.Context
import android.util.Log
import com.example.examen2parcial25_26.modelo.Contacto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

//Clase que provee de contactos en un fichero json
class ProveedorContacto {


    companion object{



        private var appContext: Context? = null
        private var _contactos: MutableList<Contacto>? = null

        fun inicializar(context: Context) {
            appContext = context.applicationContext
        }

        val contactos: MutableList<Contacto>
            get() {
                if (_contactos == null) {
                    _contactos = cargarContactosDesdeJson()
                }
                return _contactos!!
            }
        public fun grabarContactosAJson(contactos: MutableList<Contacto>) {
            try {
                val json = Json {
                    prettyPrint = true
                    encodeDefaults = true
                }

                val jsonString = json.encodeToString(contactos)

                val archivo = File(appContext!!.filesDir, "contactos.json")

                archivo.writeText(jsonString)

            } catch (e: Exception) {
                Log.e("guardarContactos", "Error al guardar contactos: ${e.message}")
            }
        }
        private fun cargarContactosDesdeJson(): MutableList<Contacto> {
            return try {
                val archivo = File(appContext!!.filesDir, "contactos.json")

                if (!archivo.exists()) {
                    mutableListOf()
                } else {
                    val jsonString = archivo.readText()

                    Json.decodeFromString(jsonString)
                }

            } catch (e: Exception) {
                Log.e("leerContactos", "Error al leer contactos: ${e.message}")
                mutableListOf()
            }
        }
    }
}
