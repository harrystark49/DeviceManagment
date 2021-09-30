package com.example.mini_proect.Activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mini_proect.R
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.save_data
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var user:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val anim = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
        c2.startAnimation(anim)

        var helper = dbHelper(this)
        var db = helper.readableDatabase

        spin.onItemSelectedListener = this
        var arr = arrayOf(getString(R.string.Admin), getString(R.string.Employee))
        var adap = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arr)
        spin.adapter = adap

        var b :Bundle? = intent.extras
        var adminEmail = b?.getString("AdminEmail")
        var empEmail = b?.getString("EmpEmail")

        // For updating the admin profile

        if(adminEmail!=null) {

            spin.visibility=View.GONE
            type.visibility=View.GONE

            var cursor = db.rawQuery("SELECT * FROM ADD_ADMIN WHERE EMAIL=?", arrayOf(adminEmail))
            if (cursor.moveToFirst()) {

                var id_index = cursor.getColumnIndex("ID")
                var id = cursor.getString(id_index).toString()
                emp_id.setText(id)
                emp_id.isEnabled= false

                var name_index = cursor.getColumnIndex("NAME")
                var name = cursor.getString(name_index).toString()
                emp_name.setText(name)

                var email_index = cursor.getColumnIndex("EMAIL")
                var email = cursor.getString(email_index).toString()
                emp_email.setText(email)

                var mobile_index = cursor.getColumnIndex("MOBILE")
                var mobile = cursor.getString(mobile_index).toString()
                emp_mobile.setText(mobile)

                cursor.close()



                emp_register_btn.setOnClickListener{

                    var Name =emp_name.text.toString()
                    var Email = emp_email.text.toString()
                    var MobileNo = emp_mobile.text.toString()


                    if(Check_for_empty_fields(id,Name,Email,MobileNo,adminEmail)) {

                        var cv=ContentValues()
                        cv.put("NAME",Name)
                        cv.put("EMAIL",Email)
                        cv.put("MOBILE",MobileNo)
                        db.update("ADD_ADMIN",cv,"ID=?", arrayOf(id))

                        Toast.makeText(this, "Successfully updated profile", Toast.LENGTH_SHORT).show()
                        var i = Intent(this,login::class.java)
                        startActivity(i)

                    }



                }

            }
        }

        //For updating the employee profile

        else if(empEmail!=null) {

            spin.setSelection(1)
            spin.isEnabled = false

            var cursor = db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", arrayOf(empEmail))
            if (cursor.moveToNext()) {

                var id_index = cursor.getColumnIndex("ID")
                var id = cursor.getString(id_index).toString()
                emp_id.setText(id)
                emp_id.isEnabled = false

                var name_index = cursor.getColumnIndex("NAME")
                var name = cursor.getString(name_index).toString()
                emp_name.setText(name)

                var email_index = cursor.getColumnIndex("EMAIL")
                var email = cursor.getString(email_index).toString()
                emp_email.setText(email)

                var mobile_index = cursor.getColumnIndex("MOBILE")
                var mobile = cursor.getString(mobile_index).toString()
                emp_mobile.setText(mobile)

                cursor.close()


                emp_register_btn.setOnClickListener {

                    var Name = emp_name.text.toString()
                    var Email = emp_email.text.toString()
                    var MobileNo = emp_mobile.text.toString()

                    if (Check_for_empty_fields(id,Name, Email, MobileNo,empEmail)) {

                        var cv = ContentValues()
                        cv.put("NAME", Name)
                        cv.put("EMAIL", Email)
                        cv.put("MOBILE", MobileNo)
                        db.update("ADD_EMPLOYEE", cv, "ID=?", arrayOf(id))
                        Toast.makeText(this, "Successfully updated profile", Toast.LENGTH_SHORT).show()
                        var i = Intent(this,login::class.java)
                        startActivity(i)

                    }
                }
            }
        }

        //For registering a new user

        else{

            emp_register_btn.setOnClickListener {

                var cursor=db.rawQuery("SELECT * FROM ADD_ADMIN",null)
                if(cursor.moveToNext() && user=="Admin"){
                    Toast.makeText(this,"Admin already Created",Toast.LENGTH_SHORT).show()
                    emp_name.setText("")
                    emp_mobile.setText("")
                    emp_id.setText("")
                    emp_email.setText("")
                    emp_id.requestFocus()
                }

                else{
                    if(Check_for_empty_fields(emp_id.text.toString()
                            ,emp_name.text.toString()
                            ,emp_email.text.toString()
                            ,emp_mobile.text.toString(),null
                        )){

                        var intent=Intent(this, save_data::class.java)
                        intent.putExtra("ID",emp_id.text.toString())
                        intent.putExtra("NAME",emp_name.text.toString())
                        intent.putExtra("EMAIL",emp_email.text.toString())
                        intent.putExtra("MOBILE",emp_mobile.text.toString())
                        intent.putExtra("AdminOrUser",user)
                        startActivity(intent)
                    }}


            }
        }
    }


    private fun Check_for_empty_fields(id: String,name : String, email: String, mobile: String,personEmail:String?):Boolean {
        var x=0;
        if(id.isEmpty()){
            material_emp_id.error=getString(R.string.IDcantbeEmpty)
            material_emp_id.isErrorEnabled=true
        }else if(!IsAlphaNumeric(id)){
            material_emp_id.error=getString(R.string.IDshouldbeAlphaNumberic)
            material_emp_id.isErrorEnabled=true
        }
        else {
            material_emp_id.isErrorEnabled=false
            x++
        }

        if(name.isEmpty()){
            material_emp_name.error=getString(R.string.NameisMandatory)
            material_emp_name.isErrorEnabled=true
        }else{
            material_emp_name.isErrorEnabled=false
            x++
        }


        if(email.isEmpty()){
            material_emp_email.error=getString(R.string.EmailisMandatory)
            material_emp_email.isErrorEnabled=true
        }
        else if(!email.contains(getString(R.string.gmailcom))){
            material_emp_email.error=getString(R.string.EntervalidEmail)
            material_emp_email.isErrorEnabled=true
        }else{

            var helper= dbHelper(this)
            var db=helper.readableDatabase

            var cursor=db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", arrayOf(emp_email.text.toString()))

            if((cursor.moveToNext()&& (!email.equals(personEmail))) ){
                material_emp_email.error=getString(R.string.Emailalreadyexists)
                material_emp_email.isErrorEnabled=true
            }else
            {
                material_emp_email.isErrorEnabled=false
                x++
            }


        }


        if(mobile.isEmpty()){
            material_emp_mobile.error=getString(R.string.MobilenumberisMandatory)
            material_emp_mobile.isErrorEnabled=true
        }else if(! isDigit(mobile)){
            material_emp_mobile.error=getString(R.string.Entervalidmobilenumber)
            material_emp_mobile.isErrorEnabled=true
        }
        else if(mobile.length!=10){
            material_emp_mobile.error= "Mobile number must contain 10 digits"
            material_emp_mobile.isErrorEnabled=true
        }
        else{
            material_emp_mobile.isErrorEnabled=false
            x++
        }
        if(x==4) {
            return true
        }
        return false
    }

    private fun isDigit(mobile: String): Boolean {
        var test=true
        var mobile_length=0
        for(num in mobile){
            if(num !in '0'..'9'){
                return false
            }
            mobile_length++
        }
        if(mobile_length!=10){
            return true
        }
        return test
    }

    private fun IsAlphaNumeric(id: String): Boolean {
        var x:Boolean=false
        var y:Boolean=false
        var z=true
        for(c in id){
            if(c in 'a'..'z' || c in 'A'..'Z'){
                x=true
            }
            else if(c in '0'..'9'){
                y=true
            }
            else{
                z=false
            }
        }
        if(x && y && z){
            return true
        }
        return false

    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        user=parent?.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
