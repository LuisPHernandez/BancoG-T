package com.example.bancogt.ui.features.login
//clase de la arquitectura de Android
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

//Almacena y gestiona datos de la interfaz de usuario
//Separa la lógica de la UI del código de presentación
//Como el "cerebro" que guarda información mientras navegas por la app
class LoginViewModel: ViewModel() {
    var user by mutableStateOf("")
    var password by mutableStateOf("")
    var checked by mutableStateOf(false)

    fun onUserChange(new: String) { user = new }
    fun onPasswordChange(new: String) { password = new }
    fun onCheckedChange(new: Boolean) { checked = new }
}