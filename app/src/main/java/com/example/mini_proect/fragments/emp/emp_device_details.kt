package com.example.mini_proect.fragments.emp

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_device__details.view.*
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.*

class emp_device_details(var email:String) : Fragment() {

    lateinit var helper: dbHelper
    lateinit var loginViewModel: All_Devices_view_Model
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\hw

        var vi=inflater.inflate(R.layout.fragment_emp_device_details, container, false)
        helper=dbHelper(vi.context)
        var db=helper.readableDatabase
        Toast.makeText(context, "ha", Toast.LENGTH_SHORT).show()

        var args=arguments
        var id=args?.get("DeviceId").toString()
        var mail=args?.get("Email").toString()

        loginViewModel = ViewModelProvider(this).get(All_Devices_view_Model::class.java)

        loginViewModel.getLoginDetailsById(context,id)!!.observe(this.viewLifecycleOwner, Observer {

            if(it != null){
                vi.emp_device_id_value1.setText(it.device_Id)
                vi.emp_os_type_value1.setText("Android")
                vi.emp_manufacture_value1.setText(it.Manufacture)
                vi.emp_os_version_value1.setText(it.Version)
                vi.emp_phn_type_value1.setText(it.phonetype)
            }
            else{
                Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()
            }
        })
        Toast.makeText(context, "$id", Toast.LENGTH_SHORT).show()

        var cursor=db.rawQuery("SELECT DEVICE_ID FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))
        if(cursor!=null && cursor.moveToNext() ){
            vi.register_device.setText("STATUS PENDING")
        }else{
            vi.register_device.setText("register device")
        }
        vi.register_device.setOnClickListener {
            loginViewModel.getLoginDetailsById(context,id)!!.observe(this.viewLifecycleOwner, Observer {
                var cv=ContentValues()
                if(it != null){
                    cv.put("DEVICE_ID",it.device_Id)
                    cv.put("MANUFACTURE",it.Manufacture)
                    cv.put("VERSION",it.Version)
                    cv.put("PHN_TYPE",it.phonetype)
                    cv.put("OS_TYPE","Android")
                    cv.put("EMAIL",email)
                    db.insert("REQUESTED_DEVICES",null,cv)
                    vi.register_device.setText("STATUS PENDING")


                }
                else{
                    Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()
                }
            })
        }
        return vi
    }

}