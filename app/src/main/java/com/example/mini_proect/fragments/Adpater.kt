package com.example.mini_proect.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.R

class Adapter(var Devices: List<All_Devices_Entity>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ID = itemView.findViewById<TextView>(R.id.deviceId)
        var Phone_type = itemView.findViewById<TextView>(R.id.phoneType)
        var MAnufacturer = itemView.findViewById<TextView>(R.id.manu)
        var Version = itemView.findViewById<TextView>(R.id.version)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var isvisible = true
        var vie: View =
            LayoutInflater.from(parent.context).inflate(R.layout.alldeviceviewitem, parent, false)
        var view = ViewHolder(vie)

        return view
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        var id: String = Devices?.get(position)!!.device_Id
        var phonetype:String = Devices?.get(position)!!.phonetype
        var manufacture:String = Devices?.get(position)!!.Manufacture
        var version:String = Devices?.get(position)!!.Version


        holder.ID.text = id
        holder.Phone_type.text = phonetype
        holder.MAnufacturer.text = manufacture
        holder.Version.text = version
    }

    override fun getItemCount(): Int {
        return Devices!!.size
    }
}

