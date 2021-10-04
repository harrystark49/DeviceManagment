package com.example.mini_proect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.fragments.all_devices
import kotlinx.android.synthetic.main.fragment_admin__device__check.view.*
import java.text.SimpleDateFormat
import java.util.*

class Admin_Device_Check(var dev_id: String, var emp_id: String?) : Fragment() {


    private val viewModel: All_Devices_view_Model = All_Devices_view_Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin__device__check, container, false)


        var helper = dbHelper(requireContext().applicationContext)
        var db = helper.readableDatabase


        var cursor = db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE ID=?", arrayOf(emp_id))


        if (cursor.moveToNext()) {
            view.empname.text = cursor.getString(cursor.getColumnIndex("NAME")).toString()
            view.empid_1.text = cursor.getString(cursor.getColumnIndex("ID")).toString()
            view.mobile_number_1.text = cursor.getString(cursor.getColumnIndex("MOBILE")).toString()
        } else {
            Toast.makeText(requireContext(), "Cursor is Empty!!", Toast.LENGTH_SHORT).show()
        }
        viewModel.getDetailsBydevId(this.requireContext(), dev_id).observe(this.viewLifecycleOwner,
            Observer {

                view.device_id_1.text = it.device_Id
                view.osversion_1.text = it.Version
                view.manufacturer_1.text = it.Manufacture
                view.phonetype_1.text = it.phonetype


            })



        view.Accept.setOnClickListener {

            val sdf = SimpleDateFormat("dd/M/yyyy \n hh:mm:ss")
            val currentDate = sdf.format(Date())

            viewModel.UpdateStartTime(this.requireContext(), emp_id!!, dev_id, currentDate, " ")
            viewModel.UpdateDevieAllocation(this.requireContext(), emp_id, dev_id)
            viewModel.RemoveFromPendingTable(this.requireContext(), dev_id)

            Toast.makeText(view.context, "Device Has Been Accepted!", Toast.LENGTH_SHORT).show()
            view.Accept.isEnabled = false
            view.decline.isEnabled = false
        }

        view.decline.setOnClickListener {
            viewModel.UpdateDevieAllocation(this.requireContext(), "false", dev_id)
            viewModel.deletePending(this.requireContext(), dev_id)
            Toast.makeText(view.context, "Device Has Been Declined!", Toast.LENGTH_SHORT).show()
            view.Accept.isEnabled = false
            view.decline.isEnabled = false
        }

        return view
    }
}
