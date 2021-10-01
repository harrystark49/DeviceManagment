package com.example.mini_proect.DataBase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class All_Devices_view_Model:ViewModel() {

    var emplogindata: LiveData<List<AllDevicesEntity>>?=null
    var logindata: LiveData<AllDevicesEntity>?=null
    var devicedata: LiveData<List<AllDevicesEntity>>?=null

    fun insertData(context: Context, phn_id:String, phn_type: String, manufacture: String,version:String,os_version:String) {
        All_Devices_Repository.insertData(context,phn_id,phn_type,manufacture,version,os_version)
    }

    fun getLoginDetailsById(context: Context?, id: String) : LiveData<AllDevicesEntity>? {
        logindata = All_Devices_Repository.getLoginDetailsById(context!!, id)
        return logindata
    }


    fun getDeviceDetails(context: Context) : LiveData<List<AllDevicesEntity>>? {
        devicedata = All_Devices_Repository.DeviceDetails(context)
        return devicedata
    }


    fun getLoginDetails(context: Context) : LiveData<List<AllDevicesEntity>>? {
        emplogindata = All_Devices_Repository.getLoginDetails(context)
        return emplogindata
    }
}