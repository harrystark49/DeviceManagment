package com.example.mini_proect.fragments.emp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.Device_Details
import com.example.mini_proect.fragments.all_devices
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.*
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.emp_device_id_value1
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.emp_manufacture_value1
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.emp_os_type_value1
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.emp_os_version_value1
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.emp_phn_type_value1
import kotlinx.android.synthetic.main.fragment_new_emp_dev_details.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewEmpDevDetails (var history:Boolean=false): Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_new_emp_dev_details, container, false)

        var helper= dbHelper(view.context)
        var db=helper.readableDatabase

        var args=arguments
        var id=args?.getString("DeviceId")
        var mail=args?.getString("Email")
        var idd=""
        var c1=db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", arrayOf(mail))

        if(c1.moveToFirst()){
            idd=c1.getString(c1.getColumnIndex("ID"))
        }
        if(history){
            view.retur.visibility=View.GONE
            view.starttime.visibility=View.VISIBLE
            view.endtime.visibility=View.VISIBLE

            var c3=db.rawQuery("SELECT * FROM DEVICE_HISTORY WHERE EMP_ID=?", arrayOf(idd))
            while(c3.moveToNext()){
                var start_time=c3.getString(c3.getColumnIndex("START_TIME"))
                var end_time=c3.getString(c3.getColumnIndex("END_TIME"))
                view.starttime_value.setText(start_time)
                view.endtime_value.setText(end_time)
            }

        }


        var loginViewModel = ViewModelProvider(this).get(All_Devices_view_Model::class.java)

        loginViewModel.getLoginDetailsById(context,id.toString())!!.observe(this.viewLifecycleOwner, Observer {

            if(it != null){
                view.emp_device_id_value1.setText(it.device_Id)
                view.emp_os_type_value1.setText(it.os_type)
                view.emp_manufacture_value1.setText(it.Manufacture)
                view.emp_os_version_value1.setText(it.Version)
                view.emp_phn_type_value1.setText(it.phonetype)
            }
            else{
                Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()
            }
        })

        view.retur.setOnClickListener {
            var cursor=db.rawQuery("SELECT * FROM ACCEPTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))
            if(cursor.moveToFirst()){
                var cv=ContentValues()
                var start=cursor.getString(cursor.getColumnIndex("START_TIME"))
                cv.put("START_TIME",start)
                val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd  HH:mm:ss ")
                val end: String = simpleDateFormat.format(Date())
                cv.put("END_TIME",end)
                cv.put("DEVICE_ID",id)
                cv.put("EMP_ID",idd)
                db.insert("DEVICE_HISTORY",null,cv)

                db.delete("ACCEPTED_DEVICES","DEVICE_ID=?", arrayOf(id))

                Toast.makeText(view.context, "success retured", Toast.LENGTH_SHORT).show()

                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.emp_fragment_replacer, all_devices("emp", ""))
                    commit()
                }

            }
        }

        return  view
    }

    }
