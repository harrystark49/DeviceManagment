package com.example.mini_proect.Adapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.requested_device_details
import kotlinx.android.synthetic.main.alldeviceviewitem.view.*


class Request_device_Adapter(
    var context: Context,
    var Devices: List<All_Devices_Entity>,
    var AdminOrEmp: String = "emp",
    var email:String
) : RecyclerView.Adapter<Request_device_Adapter.ViewHolder>() {

    lateinit var db: dbHelper
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var helper= dbHelper(context)
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
                    var frag = requested_device_details()
                    b.putString("Email",email)
                    frag.arguments = b
                    var activity = itemView.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_replacer, frag)
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

    override fun onBindViewHolder(holder: Request_device_Adapter.ViewHolder, position: Int) {
        var data = Devices[position]
        holder.setdata(data, position)
    }

    override fun getItemCount(): Int {
        return Devices!!.size
    }
}

