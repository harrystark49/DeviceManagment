package com.example.mini_proect.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.Device_Details
import com.example.mini_proect.fragments.emp.emp_device_details
import kotlinx.android.synthetic.main.alldeviceviewitem.view.*

class Adapter(
    var context: Context,
    var Devices: List<All_Devices_Entity>,
    var AdminOrEmp: String? = "emp",
    var id1: String?

) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    
    

    lateinit var db: dbHelper

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var helper = dbHelper(context)
        var db = helper.readableDatabase

        @SuppressLint("Range")
        fun setdata(data: All_Devices_Entity, position: Int) {


            data.device_Id = Devices[position].device_Id



            if (data.isAllocated == "false") {
                itemView.CL.setBackgroundColor(Color.parseColor("Green"))
            } else if (data.isAllocated == "pending" || data.isAllocated == "Pending") {
                itemView.CL.setBackgroundColor(Color.parseColor("purple"))

            } else {
                itemView.CL.setBackgroundColor(Color.parseColor("Silver"))
            }


            itemView.deviceId.text = "Device id: " + Devices[position].device_Id
            itemView.phoneType.text = "Phone type: " + Devices[position].phonetype
            itemView.manu.text = "Manufacture: " + Devices[position].Manufacture
            itemView.version.text = Devices[position].Version


            itemView.setOnClickListener {
                if (AdminOrEmp == "Admin") {

                    var b = Bundle()
                    b.putString("DeviceId", Devices[position].device_Id)

                    var frag = Device_Details()

                    frag.arguments = b

                    var activity = itemView.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_replacer, frag)
                        commit()
                    }
                } else if (id1 == Devices[position].isAllocated) {

                    var b = Bundle()
//                    b.putString("email", email)
                    b.putString("DeviceId", Devices[position].device_Id)
                    b.putString("empid",id1)


                    var frag = emp_device_details()

                    frag.arguments = b
                    var activity = itemView.context as AppCompatActivity

                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.emp_fragment_replacer, frag)
                        commit()
                    }
                } else if (Devices[position].isAllocated == "false") {

                    var b = Bundle()
                    b.putString("DeviceId", Devices[position].device_Id)
                    b.putString("empid",id1)

                    var frag = emp_device_details()
                    frag.arguments = b
                    var activity = itemView.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.emp_fragment_replacer, frag)
                        commit()
                    }


                } else {
                    Toast.makeText(
                        itemView.context,
                        "You Don't Have Access To Particular Device!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.alldeviceviewitem, parent, false)
        var view = ViewHolder(v)

        return view
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        var data = Devices[position]
        holder.setdata(data, position)
    }

    override fun getItemCount(): Int {
        return Devices!!.size
    }

//
//    fun isAlreadyAssigned(dev_id: String): Boolean {
//        var result = null
//        All_Devices_view_Model().getDetailsBydevId(this.context, dev_id).observe(this, Observer {
//            if (it.isAllocated.equals("false")) {
//                result = false
//            } else if (it.isAllocated == "Pending") {
//                result = false
//            } else {
//                result = true
//            }
//        })
//        return result
//    }
}

