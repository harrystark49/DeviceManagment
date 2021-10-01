package com.example.mini_proect.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.Adapter.RequestDeviceAdapter
import com.example.mini_proect.DataBase.AllDevicesEntity
import com.example.mini_proect.DataBase.DBHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_admin_request_devices.view.*

class AdminRequestDevices(var adminOremp:String,var email:String) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view= inflater.inflate(R.layout.fragment_admin_request_devices, container, false)
        var helper=DBHelper(view!!.context)
        var db=helper.readableDatabase
        var list=ArrayList<AllDevicesEntity>()

        var cursor=db.rawQuery("SELECT * FROM REQUESTED_DEVICES",null)

        while(cursor.moveToNext()){
            var db_id=cursor.getColumnIndex("DEVICE_ID")
            var db_mail=cursor.getColumnIndex("EMAIL")
            var db_manufacture=cursor.getColumnIndex("MANUFACTURE")
            var db_ostype=cursor.getColumnIndex("OS_TYPE")
            var db_version=cursor.getColumnIndex("VERSION")
            var db_phn_type=cursor.getColumnIndex("PHN_TYPE")

            var id=cursor.getString(db_id).toString()
            var mail=cursor.getString(db_mail)
            var manufacacture=cursor.getString(db_manufacture)
            var ostype=cursor.getString(db_ostype)
            var version=cursor.getString(db_version)
            var phntype=cursor.getString(db_phn_type)

            var data=AllDevicesEntity(id,phntype,manufacacture,version,ostype)
            list.add(data)
        }
        if(list.isEmpty()){
            view.no_device.visibility=View.VISIBLE
        }else {
            view.no_device.visibility=View.GONE
            val recyc = view.findViewById<RecyclerView>(R.id.recyclerView2)
            var recycle: RecyclerView = recyc
            var LLM: LinearLayoutManager = LinearLayoutManager(context)
            LLM.orientation = RecyclerView.VERTICAL
            recycle.layoutManager = LLM
            var adapter = RequestDeviceAdapter(view.context, list, "Admin", email)
            recycle.adapter = adapter

        }

        return view
    }

}