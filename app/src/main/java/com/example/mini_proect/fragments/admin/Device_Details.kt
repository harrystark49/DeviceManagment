package com.example.mini_proect.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_device__details.view.*
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.*

class Device_Details : Fragment() {


    lateinit var loginViewModel: All_Devices_view_Model
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var vi = inflater.inflate(R.layout.fragment_device__details, container, false)

        var args = arguments
        var id = args?.get("DeviceId").toString()

        loginViewModel = ViewModelProvider(this).get(All_Devices_view_Model::class.java)

        loginViewModel.getLoginDetailsById(context, id)!!
            .observe(this.viewLifecycleOwner, Observer {

                if (it != null) {
                    vi.emp_device_id_value.setText(it.device_Id)
                    vi.emp_os_type_value.setText(it.OsType)
                    vi.emp_manufacture_value.setText(it.Manufacture)
                    vi.emp_os_version_value.setText(it.Version)
                    vi.emp_phn_type_value.setText(it.phonetype)
                } else {
                    Toast.makeText(context, "No data!!", Toast.LENGTH_SHORT).show()

                }
            })
        return vi
    }
}