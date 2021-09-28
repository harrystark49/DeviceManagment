package com.example.mini_proect.fragments.emp

import android.annotation.SuppressLint
import android.content.Context
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
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_device__details.view.*
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.*
import java.text.SimpleDateFormat
import java.util.*

class emp_device_details : Fragment() {

    lateinit var helper: dbHelper
    lateinit var loginViewModel: All_Devices_view_Model

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var vi = inflater.inflate(R.layout.fragment_emp_device_details, container, false)
        helper = dbHelper(vi.context)
        var db = helper.readableDatabase

        vi.register_device.setText("REGISTER DEVICE")

        var args = arguments
        var id = args?.get("DeviceId").toString()
        var id1 = args?.get("empid").toString()



        Toast.makeText(this.requireContext(), "${id1} From emp_device_details", Toast.LENGTH_SHORT)
            .show()


        loginViewModel = ViewModelProvider(this).get(All_Devices_view_Model::class.java)

        loginViewModel.getLoginDetailsById(context, id)!!
            .observe(this.viewLifecycleOwner, Observer {

                if (it != null) {
                    vi.emp_device_id_value1.setText(it.device_Id)
                    vi.emp_os_type_value1.setText(it.OsType)
                    vi.emp_manufacture_value1.setText(it.Manufacture)
                    vi.emp_os_version_value1.setText(it.Version)
                    vi.emp_phn_type_value1.setText(it.phonetype)
                } else {
                    Toast.makeText(context, "No Data Found!", Toast.LENGTH_SHORT).show()
                }
            })

        loginViewModel.getDetailsBydevId(this.requireContext(), id).observe(this.viewLifecycleOwner,
            Observer {
                if (it.isAllocated == "Pending") {
                    vi.register_device.setText("STATUS PENDING")
                    vi.register_device.isEnabled = false
                } else if (it.isAllocated == "false") {
                    vi.register_device.setText("Register Device")
                } else {
                    vi.register_device.setText("Return Device")
                }
            })







        vi.register_device.setOnClickListener {

            val sdf = SimpleDateFormat("dd/M/yyyy \n hh:mm:ss")
            val currentDate = sdf.format(Date())


            if (vi.register_device.text == "Return Device") {
                All_Devices_view_Model().UpdateDevieAllocation(this.requireContext(), "false", id)

                Toast.makeText(this.requireContext(),"emp and device id's:${id} , ${id1},",Toast.LENGTH_SHORT).show()
                All_Devices_view_Model().UpdateEndTime(this.requireContext(),id1,id,currentDate)
                return@setOnClickListener
            }

            if (vi.register_device.text == "Register Device") {
                loginViewModel.getLoginDetailsById(context, id)!!
                    .observe(this.viewLifecycleOwner, Observer {

                        if (it != null) {

                            var cursor1 =
                                db.rawQuery(
                                    "SELECT * FROM ADD_EMPLOYEE WHERE ID=?",
                                    arrayOf(id1)
                                )



                            loginViewModel.UpdateDevieAllocation(requireContext(), "Pending", id)
                            loginViewModel.InsertIntoPending(requireContext(), id, id1)

                        } else {
                            Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()
                        }

                        vi.register_device.isEnabled = false

                    })


                return@setOnClickListener
            }
        }
        return vi
    }

}