package com.example.mini_proect.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.History
import com.example.mini_proect.R
import com.example.mini_proect.fragments.emp.emp_myhistory
import kotlinx.android.synthetic.main.emp_my_devices.view.*

class MydevAdapter(
    var context: Context,
    var Devices: List<All_Devices_Entity>,
    var text: String?
) : RecyclerView.Adapter<MydevAdapter.ViewHolder>() {


    val list: ArrayList<String> = arrayListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.emp_my_devices, parent, false)
        var view = ViewHolder(v)




        v.setOnClickListener {
            if (text.equals("History")) {
                var activity = parent.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.emp_fragment_replacer, emp_myhistory(list)).commit()
            }
        }

        list.clear()

        return view
    }

    override fun onBindViewHolder(holder: MydevAdapter.ViewHolder, position: Int) {


        var history: History =
            All_Devices_view_Model().getHistory(this.context, Devices[position].device_Id)


        holder.itemView.deviceIdMydev.text = Devices[position].device_Id
        holder.itemView.phoneTypeMydev.text = Devices[position].phonetype
        holder.itemView.manuMydev.text = Devices[position].Manufacture
        holder.itemView.versionMydev.text = Devices[position].Version



        list.add(Devices[position].device_Id)
        list.add(Devices[position].Manufacture)
        list.add(Devices[position].phonetype)
        list.add(Devices[position].OsType)
        list.add(Devices[position].Version)
        list.add(history.startTme)
        list.add(history.EndTime)

    }

    override fun getItemCount(): Int {
        return Devices!!.size
    }
}

