package com.example.ejemplonavegacioncompose.navegacion

import androidx.collection.emptyLongSet
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ejemplonavegacioncompose.data.UsuarioProveedor
import com.example.ejemplonavegacioncompose.model.Usuario
import com.example.ejemplonavegacioncompose.ui.componentes.BottomBar
import com.example.ejemplonavegacioncompose.ui.pantallas.HomeScreen
import com.example.ejemplonavegacioncompose.ui.pantallas.LoginScreen
import com.example.ejemplonavegacioncompose.ui.pantallas.ProfileScreen

//Aqui se define el NavHost y
//toda la navegación
@Composable
fun NavGraph() {
    //Defino el el controlador de navegación que es necesario
    //para navegar
    val navController = rememberNavController()






    Scaffold(
        //Le paso al BottomBar el navController para que controle la navegación
        bottomBar = { BottomBar(navController) }
    ) { padding ->

        //Invoco a la función Composable NavHost que se invocará cuando
        //exista un cambio en el destino
        NavHost(
            navController = navController,
            startDestination = Login, //Ruta inicial, es decir, lo primero que quiero que aparezca
            modifier = Modifier.padding(padding)
        ) {
            //Defino las rutas, en este caso como objetos
            composable<Login> {
                LoginScreen { user,pass->
                   UsuarioProveedor.usuarios.find { it.nombre == user && it.password == pass }?.let{usuario->
                     //Si hay un usuario con esas credenciales navego a Home
                     navController.navigate(Home(usuario.nombre,usuario.foto))


                   }
                    }

            }
            composable<Home>{ navBackStackEntry->
                //navBackStackEntry contiene la información de la ruta
                //obtengo la ruta
                val ruta = navBackStackEntry.toRoute<Home>()
                //Ya puedo acceder desde la ruta a los parametros usuario y foto que se le pasaron

                HomeScreen(ruta.usuario,ruta.avatar,onLogout = {
                    //Navego a Login
                    navController.navigate(Login)

                })
            }
            composable<Perfil>{
                ProfileScreen()
            }
        }
    }
}
