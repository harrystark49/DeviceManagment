package com.example.mini_proect.DataBase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class All_Devices_view_Model:ViewModel() {

    var emplogindata: LiveData<List<All_Devices_Entity>>?=null
    var logindata: LiveData<All_Devices_Entity>?=null
    var Devicedata: LiveData<List<All_Devices_Entity>>?=null

    fun insertData(context: Context, phn_id:String, phn_type: String, manufacture: String,version:String,isAllocated:Boolean) {
        All_Devices_Repository.insertData(context,phn_id,phn_type,manufacture,version,isAllocated)
    }

    fun getLoginDetailsById(context: Context?, id: String) : LiveData<All_Devices_Entity>? {
        logindata = All_Devices_Repository.getLoginDetailsById(context!!, id)
        return logindata
    }


    fun getDeviceDetails(context: Context) : LiveData<List<All_Devices_Entity>>? {
        Devicedata = All_Devices_Repository.DeviceDetails(context)
        return Devicedata
    }


    fun getLoginDetails(context: Context) : LiveData<List<All_Devices_Entity>>? {
        emplogindata = All_Devices_Repository.getLoginDetails(context)
        return emplogindata
    }
}