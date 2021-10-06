package com.example.mini_proect.Adapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.AllDevicesEntity
import com.example.mini_proect.DataBase.DBHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.requested_device_details
import kotlinx.android.synthetic.main.alldeviceviewitem.view.*


class RequestDeviceAdapter(
    var context: Context,
    var devices: List<AllDevicesEntity>,
    var AdminOrEmp: String = "emp",
    var email:String
) : RecyclerView.Adapter<RequestDeviceAdapter.ViewHolder>() {

    lateinit var db: DBHelper
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var helper= DBHelper(context)
        var db=helper.readableDatabase

        fun setData(data: AllDevicesEntity, position: Int) {
            var s=devices[position].device_Id
            data.device_Id = devices[position].device_Id
            var cursor=db.rawQuery("SELECT DEVICE_ID FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(s))
            if(cursor!=null && cursor.moveToNext() ){
                itemView.setBackgroundColor(-7829368)
            }

            data.Manufacture = devices[position].Manufacture
            data.Version = devices[position].Version
            data.phonetype = devices[position].phonetype

            itemView.deviceId.text="Device id: "+devices[position].device_Id
            itemView.phoneType.text="Phone type: "+devices[position].phonetype
            itemView.manu.text="Manufacture: "+devices[position].Manufacture
            itemView.version.text=devices[position].Version


            itemView.setOnClickListener {

                if (AdminOrEmp == "Admin") {
                    var b: Bundle = Bundle()
                    b.putString("DeviceId", devices[position].device_Id)
                    var frag = requested_device_details()
                    b.putString("Email",email)
                    frag.arguments = b
                    var activity = itemView.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_replacer, frag)
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

    override fun onBindViewHolder(holder: RequestDeviceAdapter.ViewHolder, position: Int) {
        var data = devices[position]
        holder.setData(data, position)
    }

    override fun getItemCount(): Int {
        return devices!!.size
    }
}

