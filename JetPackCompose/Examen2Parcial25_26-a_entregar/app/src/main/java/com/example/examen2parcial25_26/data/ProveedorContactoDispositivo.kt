package com.example.examen2parcial25_26.data

import android.content.Context
import android.provider.ContactsContract
import com.example.examen2parcial25_26.modelo.ContactoDispositivo

//Clase que provee los contactos del dispositivo
//Desde ProveedorContactoDispositivo.contactosDispositivos tengo un List de
//contactosDispositivos
class ProveedorContactoDispositivo {
    companion object {

        private var appContext: Context? = null
        private var _contactosDispositivos: List<ContactoDispositivo>? = null
        val contactosDispositivo: List<ContactoDispositivo>
            get() {
                if (_contactosDispositivos == null) {
                    appContext?.let { _contactosDispositivos = obtener_contactos() }

                }
                return _contactosDispositivos!!
            }

        //Funcion que hay que llamar al principio de la activity
        fun inicializar(context: Context) {
            appContext = context.applicationContext
        }


        private fun obtener_contactos(): List<ContactoDispositivo> {
            val contactList = mutableListOf<ContactoDispositivo>()

            val cursor = appContext!!.contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME),
                null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            cursor?.use {
                val idIndex = it.getColumnIndex(ContactsContract.Contacts._ID)
                val nameIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

                while (it.moveToNext()) {
                    val id = it.getString(idIndex)
                    val nombre = it.getString(nameIndex)
                    //   val tel = obtener_telefono(context, id)
                    val email = obtener_email(id)
                    contactList.add(ContactoDispositivo(id, nombre, "", email))
                }
            }

            return contactList
        }

        private fun obtener_email(id: String): String? {
            var email: String? = null

            val emailCursor = appContext!!.contentResolver.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                arrayOf(ContactsContract.CommonDataKinds.Email.ADDRESS),
                "${ContactsContract.CommonDataKinds.Email.CONTACT_ID} = ?",
                arrayOf(id),
                null
            )

            emailCursor?.use {
                val index = it.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)
                if (it.moveToNext()) {
                    email = it.getString(index).toString()
                }


            }
            return email


        }


        //Define la función obtener telefono, necesita los mismos argumentos
        //que obtener email, devuelve un String?
        //Es una funcion exactamente igual que la anterior
        //contentURI del proveedor de contenidos es: ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        //El campo es arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
        //El filtro(selection) es "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?"
        //El valor del filtro(selectionArgs) es arrayOf(contactId)

        fun obtener_telefono(contactId: String): String? {
            var telefono: String? = null
            val telefonoCursor = appContext!!.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                arrayOf(contactId),
                null
            )
            telefonoCursor?.use {
                val index = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)
                if (it.moveToNext()) {
                    telefono = it.getString(index).toString()
                }


            }
            return telefono
        }

    }
}