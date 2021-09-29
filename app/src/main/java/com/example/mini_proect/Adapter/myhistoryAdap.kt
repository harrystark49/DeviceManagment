package com.example.mini_proect.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.Activities.EmpMyHistory
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.History
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import com.example.mini_proect.deviceDetails
import com.example.mini_proect.fragments.admin.Device_Details
import com.example.mini_proect.fragments.all_devices
import com.example.mini_proect.fragments.emp.emp_device_details
import kotlinx.android.synthetic.main.alldeviceviewitem.view.*

class myhistoryAdap(
    var context: Context,
    var list: List<History>,
    var lco: LifecycleOwner
) : RecyclerView.Adapter<myhistoryAdap.ViewHolder>() {


    lateinit var db: dbHelper

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var helper = dbHelper(context)
        var db = helper.readableDatabase

        @SuppressLint("Range")
        fun setdata(data: History, position: Int) {

            var b = Bundle()

            var deviceId = data.device_Id
            var empId = data.emp_id

            itemView.deviceId.text = deviceId

            var activity = itemView.context as AppCompatActivity



            All_Devices_view_Model().getDetailsBydevId(itemView.context, deviceId)
                .observe(activity, Observer {

                    b.putString("devId", deviceId)
                    b.putString("ostype", it.OsType)
                    b.putString("osversion", it.Version)
                    b.putString("manfact", it.Manufacture)
                    b.putString("phonetype", it.phonetype)

                    itemView.phoneType.text = it.phonetype
                    itemView.manu.text = it.Manufacture
                    itemView.version.text = it.Version
                })

            var startTime = data.startTme
            var endTime = data.EndTime

            b.putString("startTime", startTime)
            b.putString("endtime", endTime)

            itemView.setOnClickListener {

                var frag = myhist()
                frag.arguments = b


                activity.supportFragmentManager.beginTransaction().replace(R.id.frL, frag).commit()
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

    override fun onBindViewHolder(holder: myhistoryAdap.ViewHolder, position: Int) {

        holder.itemView.CL.setBackgroundColor(Color.parseColor("White"))

        var data = list[position]
        holder.setdata(data, position)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

}
