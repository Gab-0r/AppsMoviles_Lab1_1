package com.example.appsmoviles_lab1_1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
class MainViewModel: ViewModel() {

    private var borndate: MutableLiveData<String> = MutableLiveData()
    val borndateDone: LiveData<String> = borndate

    private var nombre: MutableLiveData<String> = MutableLiveData()
    val nombreDone: LiveData<String> = nombre

    private var apellido: MutableLiveData<String> = MutableLiveData()
    val apellidoDone: LiveData<String> = apellido

    private var email: MutableLiveData<String> = MutableLiveData()
    val emailDone: LiveData<String> = email

    private var pass: MutableLiveData<String> = MutableLiveData()
    val passDone: LiveData<String> = pass

    private var confpass: MutableLiveData<String> = MutableLiveData()
    val confpassDone: LiveData<String> = confpass

    private var isPassOk: MutableLiveData<Boolean> = MutableLiveData()
    val isPassOkDone: LiveData<Boolean> = isPassOk

    private var ciudadnac: MutableLiveData<String> = MutableLiveData()
    val ciudadnacDone: LiveData<String> = ciudadnac

    private var genero: MutableLiveData<String> = MutableLiveData()
    val generoDone: LiveData<String> = genero

    private var hobbies: MutableLiveData<String> = MutableLiveData()
    val hobbiesDone: LiveData<String> = hobbies

    private var position = 0
    private var emptyFieldAt: MutableLiveData<Int> = MutableLiveData()
    val emptyFieldAtDone: LiveData<Int> = emptyFieldAt

    private var showString: MutableLiveData<String> = MutableLiveData()
    val showStringDone: LiveData<String> = showString

    fun setBornDate(borndate_: String){
        borndate.value = borndate_
    }

    fun areEmptyFields(name_field: Boolean, lastname_field: Boolean,
                       email_field: Boolean,  pass_field: Boolean, confpass_field: Boolean){
        var fields = booleanArrayOf(name_field, lastname_field, email_field, pass_field, confpass_field)
        for (i in fields.indices) {
            Log.d("Verificando campo: ", i.toString() + fields.get(i).toString() )
            if (fields.get(i)) {
                position = i + 1
                Log.d("Campo vacío en: ", position.toString())
                emptyFieldAt.value = position
                break
            }
        }
    }

    fun storeFields(name_:String, lastname_:String, email_: String, pass_: String, confpass_:String, city: String){
        nombre.value = name_
        apellido.value = lastname_
        email.value = email_
        pass.value = pass_
        confpass.value = confpass_
        ciudadnac.value = city
    }

    fun storeGenre(genre: String){
        genero.value = genre
    }

    fun isPassCorrect(){
        if(pass.value != confpass.value){
            isPassOk.value = false
        }
    }

    fun storeHobbies(hob_:String){
        hobbies.value += hob_ + " "
    }

    fun showInfo(){
        showString.value = "Nombre: " + nombre.value + "\n" +
        "Apellido: " + apellido.value + "\n" +
        "E-mail: " + email.value + "\n" +
        "Contraseña: " + pass.value + "\n" +
        "Confirmar contraseña: " + confpass.value + "\n" +
        "Genero: " + genero.value + "\n" +
        "Hobbies: " + hobbies.value + "\n" +
        "Ciudad de nacimiento: " + ciudadnac.value + "\n" +
        "Fecha de nacimiento: " + borndate.value
    }


}