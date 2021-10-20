package com.example.mini_proect.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.AllDevicesEntity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.DBHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.Device_Details
import com.example.mini_proect.fragments.emp.EmpDeviceDetails
import kotlinx.android.synthetic.main.alldeviceviewitem.view.*

class Adapter(
    var context: Context,
    var devices: ArrayList<AllDevicesEntity>,
    var AdminOrEmp: String = "emp",
    var email:String
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var db:DBHelper
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var helper=DBHelper(context)
        var db=helper.readableDatabase

        fun setData(data: AllDevicesEntity, position: Int) {
            var s=devices[position].device_Id
            data.device_Id = devices[position].device_Id
            var cursor1=db.rawQuery("SELECT DEVICE_ID FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(s))
            var cursor=db.rawQuery("SELECT DEVICE_ID FROM ACCEPTED_DEVICES WHERE DEVICE_ID=?", arrayOf(s))
            if(cursor1.moveToNext() || cursor.moveToNext() ){
                itemView.setBackgroundColor(-7829368)
            }else{
                itemView.setBackgroundColor(-16711936)
            }

            data.Manufacture = devices[position].Manufacture
            data.Version = devices[position].Version
            data.phonetype = devices[position].phonetype

            itemView.deviceId.text="Device id: "+devices[position].device_Id
            itemView.phoneType.text="Phone type: "+devices[position].phonetype
            itemView.manu.text="Manufacture: "+devices[position].Manufacture
            itemView.version.text=devices[position].Version



            itemView.setOnLongClickListener {
                if (AdminOrEmp == "Admin"){
                    var cursor2=db.rawQuery("SELECT DEVICE_ID FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(s))
                    var cursor3=db.rawQuery("SELECT DEVICE_ID FROM ACCEPTED_DEVICES WHERE DEVICE_ID=?", arrayOf(s))
                    if(!(cursor2.moveToNext() || cursor3.moveToNext())){
                        All_Devices_view_Model().delete(context,devices[position].device_Id)
                        devices.remove(data)
                        notifyDataSetChanged()
                    }
                }
                true
            }




            itemView.setOnClickListener {

                if (AdminOrEmp == "Admin") {
                    var b = Bundle()
                    b.putString("DeviceId", devices[position].device_Id)
                    var frag = Device_Details()
                    frag.arguments = b
                    var activity = itemView.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_replacer, frag)
                        addToBackStack("frag")
                        commit()
                    }
                } else {
                    var b = Bundle()
                    b.putString("DeviceId", devices[position].device_Id)
                    b.putString("Email",email)
                    var frag = EmpDeviceDetails(email)
                    frag.arguments = b
                    var activity = itemView.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.emp_fragment_replacer, frag)
                        addToBackStack("frag")
                        commit()
                    }
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
        var data = devices[position]
        holder.setData(data, position)
    }

    override fun getItemCount(): Int {
        return devices!!.size
    }
}

