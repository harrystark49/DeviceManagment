package com.example.mini_proect

import android.annotation.SuppressLint
import android.bluetooth.BluetoothClass
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.dbHelper
import kotlinx.android.synthetic.main.fragment_device__details.view.*
import kotlinx.android.synthetic.main.fragment_device_details.*
import kotlinx.android.synthetic.main.fragment_device_details.view.*

class deviceDetails : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_device_details, container, false)
        var b = arguments

        var helper = dbHelper(this.requireContext())
        var db = helper.readableDatabase


        if (b?.getString("isallocated") == "Pending") {

            var p = All_Devices_view_Model().getPendingDevicesById(
                this.requireContext(),
                b?.getString("deviceid")!!
            )

            var emp_id = p.emp_id
            view.empidvalue.text = emp_id
            view.statusvalue.text = "Pending"


            var c = db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE ID=?", arrayOf(emp_id))
            if (c.moveToFirst()) {
                view.deviceusedvalue.text = c.getString(c.getColumnIndex("NAME")).toString()
                view.mobilenumbervalue.text = c.getString(c.getColumnIndex("MOBILE")).toString()
            }

        }



        if (b?.getString("isallocated") != "Pending" && b?.getString("isallocated") != "false") {

            var p = All_Devices_view_Model().getPendingDevicesById(
                this.requireContext(),
                b?.getString("deviceid")!!
            )

            var emp_id = b?.getString("isallocated")
            view.empidvalue.text = emp_id
            view.statusvalue.text = "Granted"


            var c = db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE ID=?", arrayOf(emp_id))
            if (c.moveToFirst()) {
                view.deviceusedvalue.text = c.getString(c.getColumnIndex("NAME")).toString()
                view.mobilenumbervalue.text = c.getString(c.getColumnIndex("MOBILE")).toString()
            }


        }



        view.emp_device_id_value1.text = b?.getString("deviceid")
        view.emp_os_type_value1.text = b?.getString("ostype")
        view.emp_os_version_value1.text = b?.getString("version")
        view.emp_manufacture_value1.text = b?.getString("manufacturer")
        view.emp_phn_type_value1.text = b?.getString("phonetype")


        return view
    }
}
