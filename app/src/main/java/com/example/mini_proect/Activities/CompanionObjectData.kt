package com.example.mini_proect.Activities

class CompanionObjectData {

    companion object data{
        var email:String = ""
        var password:String = ""
        var type:String = ""
        fun loginDetails(mail:String, pass:String, personType:String){
            email = mail
            password = pass
            type = personType
        }

    }
}