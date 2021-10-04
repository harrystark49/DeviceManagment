package com.example.mini_proect.DataBase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Query

class All_Devices_view_Model : ViewModel() {


    companion object {
        fun initializeDb(context: Context?): All_Devices_Db {
            return All_Devices_Db.databaseclient(context!!)
        }


        private var data: All_Devices_Db? = null

    }

    var Devicedata: LiveData<List<All_Devices_Entity>>? = null

    fun insertData(
        context: Context,
        phn_id: String,
        phn_type: String,
        os_type: String,
        manufacture: String,
        version: String,
        isAllocated: String
    ) {
        All_Devices_Repository.insertData(
            context,
            phn_id,
            phn_type,
            os_type,
            manufacture,
            version,
            isAllocated
        )
    }

    fun getLoginDetailsById(context: Context?, id: String): LiveData<All_Devices_Entity> {
        return All_Devices_Repository.getDeviceDetailsById(context!!, id)
    }


    fun getDetailsBydevId(context: Context, id: String): LiveData<All_Devices_Entity> {
        data = initializeDb(context)

        return data!!.All_Devices_Dao().getDetailsById(id)
    }


    fun RemoveFromPendingTable(context: Context, id: String) {
        data = initializeDb(context)
        data!!.All_Devices_Dao().RemoveFromPendingDevices(id)

    }

    fun getStartTime(context: Context,emp_id: String?, dev_id:String):History{
        data = initializeDb(context)
        return data!!.All_Devices_Dao().getStartTime(emp_id,dev_id)
    }



    fun getDeviceDetails(context: Context): LiveData<List<All_Devices_Entity>>? {
        Devicedata = All_Devices_Repository.DeviceDetails(context)
        return Devicedata
    }


    fun deletePending(context: Context, dev_id: String) {
        data = initializeDb(context)
        data!!.All_Devices_Dao().deletepending(dev_id)
    }


    fun getMyDevices(context: Context, emp_id: String): LiveData<List<All_Devices_Entity>> {
        data = initializeDb(context)
        return data!!.All_Devices_Dao().getMyDevices(emp_id)
    }


    fun UpdateDevieAllocation(context: Context?, emp_id: String?, dev_id: String) {
        All_Devices_Repository.UpdateDevieAllocation(context, emp_id, dev_id)
    }


    fun getDetailsById(context: Context, id: String): Pending_Devices {
        return All_Devices_Repository.getDetailsById(context, id)
    }


    fun InsertIntoPending(context: Context?, dev_id: String, emp_id: String?) {
        All_Devices_Repository.InsertIntoPending(context, Pending_Devices(dev_id, emp_id))
    }


    fun getPendingDevices(context: Context): LiveData<List<Pending_Devices>> {
        return All_Devices_Repository.getPendingDevices(context)
    }


    fun UpdateStartTime(
        context: Context,
        emp_id: String,
        dev_id: String,
        time: String,
        endTime: String
    ) {
        data = initializeDb(context)
        data!!.All_Devices_Dao().UpdateStartTime(emp_id, dev_id, time, endTime)
    }


    fun getPendingDevicesById(context: Context,dev_id: String): Pending_Devices{
        data = initializeDb(context)
        return data!!.All_Devices_Dao().getPendingDevicesById(dev_id)
    }



    fun UpdateEndTime(context: Context, emp_id: String, dev_id: String,endtime: String) {
        data = initializeDb(context)
        data!!.All_Devices_Dao().UpdateEndTime(emp_id, dev_id, endtime)
    }

    fun getHistory(context: Context, dev_id: String):History{
        data = initializeDb(context)
        return data!!.All_Devices_Dao().getHistory(dev_id)

    }



    fun getAllDevicesHistory(context: Context,emp_id: String):List<History>{
        data = initializeDb(context)
        return data!!.All_Devices_Dao().getAllDevicesHistory(emp_id)

    }

}