package com.example.mini_proect.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.Device_Details
import com.example.mini_proect.fragments.emp.emp_device_details

class Adapter(var context: Context,var Devices: List<All_Devices_Entity>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setdata(data: All_Devices_Entity, position: Int) {

            data.device_Id=Devices[position].device_Id
            data.Manufacture=Devices[position].Manufacture
            data.Version=Devices[position].Version
            data.phonetype=Devices[position].phonetype

            itemView.setOnClickListener {
                var b:Bundle= Bundle()
                b.putString("DeviceId",Devices[position].device_Id)
                var frag=Device_Details()
                frag.arguments=b
                var activity=itemView.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_replacer,frag)
                    commit()
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View =
            LayoutInflater.from(parent.context).inflate(com.example.mini_proect.R.layout.alldeviceviewitem, parent, false)
        var view = ViewHolder(v)

        return view
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        var data=Devices[position]
        holder.setdata(data,position)
    }

    override fun getItemCount(): Int {
        return Devices!!.size
    }
}

