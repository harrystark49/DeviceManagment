package com.example.mini_proect.DataBase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class All_Devices_view_Model:ViewModel() {

    var emplogindata: LiveData<All_Devices_Entity>?=null

    fun insertData(context: Context, phn_id:String, phn_type: String, manufacture: String,version:String) {
        All_Devices_Repository.insertData(context,phn_id,phn_type,manufacture,version)
    }

    fun getLoginDetails(context: Context, id: String) : LiveData<All_Devices_Entity>? {
        emplogindata = All_Devices_Repository.getLoginDetails(context, id)
        return emplogindata
    }
}