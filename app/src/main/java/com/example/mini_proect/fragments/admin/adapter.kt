package com.example.mini_proect.fragments.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.R
import com.example.mini_proect.fragments.dataclass

class adapter(var list:ArrayList<dataclass>) :RecyclerView.Adapter<adapter.ViewHolder>(){
    class ViewHolder( itemView: View):RecyclerView.ViewHolder(itemView) {

        var id=itemView.findViewById<TextView>(R.id.deviceId)
        var eID = itemView.findViewById<TextView>(R.id.EmployeeID)
        var MAnufacturer = itemView.findViewById<TextView>(R.id.manufacture_)
        var ENAME = itemView.findViewById<TextView>(R.id.EMployeeNAme)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        var vie: View =
            LayoutInflater.from(parent.context).inflate(R.layout.requestdevicelist, parent, false)
        var view = ViewHolder(vie)

        return view
    }

    override fun onBindViewHolder(holder: adapter.ViewHolder, position: Int) {
        var Id:String=list?.get(position)!!.device_Id
        var emid: String =list?.get(position)!!.emid
        var manu: String =list.get(position)!!.Manufacture
        var emname: String =list.get(position)!!.emname
        holder.id.text=Id
        holder.eID.text=emid
        holder.MAnufacturer.text=manu
        holder.ENAME.text=emname

    }

    override fun getItemCount(): Int {
        return list.size
    }
}