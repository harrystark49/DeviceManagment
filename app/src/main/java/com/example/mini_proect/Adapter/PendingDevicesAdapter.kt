package com.example.mini_proect.Adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.Admin_Device_Check
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.Pending_Devices
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.alldeviceviewitem.view.deviceId
import kotlinx.android.synthetic.main.requestdevicelist.view.*

class PendingDevicesAdapter(
    var context: Context,
    var Devices: List<Pending_Devices>,

    ) : RecyclerView.Adapter<PendingDevicesAdapter.ViewHolder>() {


    private lateinit var dev_id: String

    private lateinit var activity: AppCompatActivity


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var Name: String


        fun setData(dev_id: String, emp_id: String?) {


            var helper = dbHelper(context)
            var db = helper.readableDatabase

            var cursor: Cursor =
                db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE ID=?", arrayOf(emp_id))

            if (cursor.moveToFirst()) {
                var inx = cursor.getColumnIndex("NAME")
                Name = cursor.getString(inx)
            } else {
                Toast.makeText(itemView.context, "Cursor is Empty!!", Toast.LENGTH_SHORT).show()
            }

            itemView.deviceId.text = dev_id
            itemView.EmployeeID.text = Name
            itemView.EMployeeNAme.text = emp_id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        activity = parent.context as AppCompatActivity
        var v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.requestdevicelist, parent, false)
        var view = ViewHolder(v)

        v.setOnClickListener {
            activity.supportFragmentManager.beginTransaction()
                .replace(
                    R.id.FARD,
                    Admin_Device_Check(
                        v.deviceId.text.toString(),
                        Devices[view.adapterPosition].emp_id
                    )
                ).addToBackStack(null).commit()
        }
        return view
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        dev_id = Devices[position].device_Id
        var emp_id1 = Devices[position].emp_id!!

        var viewModel = All_Devices_view_Model()

        viewModel.getDetailsById(activity.applicationContext, dev_id)


        var viewModel1 = All_Devices_view_Model()
        viewModel1.getDetailsBydevId(this.context, dev_id).observe(this.activity,
            Observer {
                holder.itemView.manufacture_.text = it.Manufacture
            })



        holder.setData(dev_id, emp_id1)

    }

    override fun getItemCount(): Int {
        return Devices!!.size
    }
}

