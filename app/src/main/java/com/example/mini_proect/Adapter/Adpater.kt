package com.example.mini_proect.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.Device_Details
import com.example.mini_proect.fragments.emp.emp_device_details
import kotlinx.android.synthetic.main.alldeviceviewitem.view.*
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.*

class Adapter(
    var context: Context,
    var Devices: List<All_Devices_Entity>,
    var AdminOrEmp: String = "emp",
    var email:String
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var db:dbHelper
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var helper=dbHelper(context)
        var db=helper.readableDatabase

        fun setdata(data: All_Devices_Entity, position: Int) {
            var s=Devices[position].device_Id
            data.device_Id = Devices[position].device_Id
            var cursor=db.rawQuery("SELECT DEVICE_ID FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(s))
            if(cursor!=null && cursor.moveToNext() ){
                itemView.setBackgroundColor(-7829368)
            }

            data.Manufacture = Devices[position].Manufacture
            data.Version = Devices[position].Version
            data.phonetype = Devices[position].phonetype

            itemView.deviceId.text="Device id: "+Devices[position].device_Id
            itemView.phoneType.text="Phone type: "+Devices[position].phonetype
            itemView.manu.text="Manufacture: "+Devices[position].Manufacture
            itemView.version.text=Devices[position].Version


            itemView.setOnClickListener {

                if (AdminOrEmp == "Admin") {
                    var b: Bundle = Bundle()
                    b.putString("DeviceId", Devices[position].device_Id)
                    var frag = Device_Details()
                    b.putString("Email",email)
                    frag.arguments = b
                    var activity = itemView.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_replacer, frag)
                        commit()
                    }
                } else {
                    var b: Bundle = Bundle()
                    b.putString("DeviceId", Devices[position].device_Id)
                    //b.putString("Email",email)
                    var frag = emp_device_details(email)
                    frag.arguments = b
                    var activity = itemView.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.emp_fragment_replacer, frag)
                        commit()
                    }
                }


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View =
            LayoutInflater.from(parent.context)
                .inflate(com.example.mini_proect.R.layout.alldeviceviewitem, parent, false)
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
}

