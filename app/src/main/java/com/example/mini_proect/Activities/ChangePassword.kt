package com.example.mini_proect.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        var helper= dbHelper(this)
        var db=helper.readableDatabase

        var OldPass= old_password.text.toString()
        val NewPass= new_password.text.toString()
        val ConNew = admin_confirm_password.text.toString()
        submit_button.setOnClickListener {



        }
    }
    private fun IsAlphaNumeric(id: String): Boolean {
        var x:Boolean=false
        var y:Boolean=false
        var z:Boolean = true
        for(c in id){
            if(c in 'a'..'z')
                x=true

            else if(c in '0'..'9')
                y=true

            else
                z=false

        }
        if(x && y&& z){
            return true
        }
        return false
    }

}