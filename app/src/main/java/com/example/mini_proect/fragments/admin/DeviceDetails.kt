package com.example.mini_proect.fragments.admin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.DBHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_device__details.view.*

class Device_Details : Fragment() {

    lateinit var helper: DBHelper

    lateinit var loginViewModel: All_Devices_view_Model
    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var vi = inflater.inflate(R.layout.fragment_device__details, container, false)
        helper = DBHelper(vi.context)
        var db = helper.readableDatabase

        var args = arguments
        var id = args?.get("DeviceId").toString()
        var emp_id = args?.getString("Email")


        loginViewModel = ViewModelProvider(this).get(All_Devices_view_Model::class.java)

        loginViewModel.getLoginDetailsById(context, id)!!
            .observe(this.viewLifecycleOwner, Observer {

                if (it != null) {
                    vi.emp_device_id_value.setText(it.device_Id)
                    vi.emp_os_type_value.setText(it.os_type)
                    vi.emp_manufacture_value.setText(it.Manufacture)
                    vi.emp_os_version_value.setText(it.Version)
                    vi.emp_phn_type_value.setText(it.phonetype)
                } else {
                    Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()

                }
            })

        var cursor = db.rawQuery("SELECT * FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))
        var cursor1 = db.rawQuery("SELECT * FROM ACCEPTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))

        var mailid=""
        if(cursor1.moveToNext()){
            mailid=cursor1.getString(cursor1.getColumnIndex("EMP_ID"))
        }
        if (cursor.moveToFirst() || cursor1.moveToFirst()) {
            vi.emp_details.visibility = View.VISIBLE
            if (cursor.moveToFirst()) {
                vi.start.setText("--")
                vi.statusvalue.setText("Pending")

            }
            if (cursor1.moveToFirst()) {

                var time_index = cursor1.getColumnIndex("START_TIME")
                var time = cursor1.getString(time_index)
                vi.start.setText(time)
                vi.statusvalue.setText("Assigned")

            }


            var cursor2 = db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE ID=?", arrayOf(mailid))

            if (cursor2.moveToNext()) {
                var name_index = cursor2.getColumnIndex("NAME")
                var name = cursor2.getString(name_index)
                var id_index = cursor2.getColumnIndex("ID")
                var idv = cursor2.getString(id_index)
                var mobile_index = cursor2.getColumnIndex("MOBILE")
                var mobile = cursor2.getString(mobile_index)
                vi.device_used_value1.setText(name)
                vi.empID_value1.setText(idv)
                vi.mobile_number_value1.setText(mobile)
            }
        }

        return vi
    }

}