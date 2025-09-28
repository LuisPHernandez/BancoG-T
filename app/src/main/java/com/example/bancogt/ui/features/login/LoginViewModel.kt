package com.example.bancogt.ui.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var user by mutableStateOf("")
    var password by mutableStateOf("")
    var checked by mutableStateOf(false)

    fun onUserChange(new: String) { user = new }
    fun onPasswordChange(new: String) { password = new }
    fun onCheckedChange(new: Boolean) { checked = new }
}