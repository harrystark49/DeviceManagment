package com.example.mini_proect.fragments.emp

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
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.*

class NewEmpDevDetails : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_new_emp_dev_details, container, false)

        var helper= dbHelper(view.context)
        var db=helper.readableDatabase


        var args=arguments
        var id=args?.getString("DeviceId")


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

        return  view
    }

    }
