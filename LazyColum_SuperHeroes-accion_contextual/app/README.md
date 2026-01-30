#Ejercicio 2- Diapositiva 123

##Solución
Cuando se borra un superheroe, se quedaba seleccionado el siguiente y el modo action_mode seguía activa, entonces lo que se ha hecho 
es tras borrar el superheroe se desactiva ese modo

Se ha movido las variables action_mode y mostrar_dialogo al viewmodel y para que se pueda modificar su estado se han creado funciones que modifican el estado a lo contrario
que tenía almacenado 

Se han modificado casi todas las funciones para que reciben un objeto de tipo Superheroe