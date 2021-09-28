package com.example.mini_proect.DataBase

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class All_Devices_Repository {

    companion object {

        private var data: All_Devices_Db? = null
        private lateinit var livedata: LiveData<List<All_Devices_Entity>>
        private lateinit var ldata: LiveData<All_Devices_Entity>
        private lateinit var AllDeviceslivedata: LiveData<List<All_Devices_Entity>>


        private fun initializeDb(context: Context?): All_Devices_Db {
            return All_Devices_Db.databaseclient(context!!)
        }

        fun insertData(
            context: Context,
            id: String,
            phn_type: String,
            os_type:String,
            maufacture: String,
            version: String,
            isAllocated: String
        ) {

            data = initializeDb(context)

            CoroutineScope(Dispatchers.IO).launch {

                val loginDetails = All_Devices_Entity(id, phn_type,os_type, maufacture, version, "false")
                data!!.All_Devices_Dao().insertData(loginDetails)
            }

        }

        fun getLoginDetailsById(context: Context?, id: String): LiveData<All_Devices_Entity> {

            data = initializeDb(context)

            return data!!.All_Devices_Dao().getDetailsById(id)

        }

        fun DeviceDetails(context: Context): LiveData<List<All_Devices_Entity>> {

            data = initializeDb(context)

            AllDeviceslivedata = data!!.All_Devices_Dao().getDetails()

            return AllDeviceslivedata
        }


        fun getLoginDetails(context: Context): LiveData<List<All_Devices_Entity>>? {

            data = initializeDb(context)

            livedata = data!!.All_Devices_Dao().getDetails()

            return livedata
        }


        fun UpdateDevieAllocation(context: Context?, emp_id: String?, dev_id: String) {

            data = initializeDb(context)
            data!!.All_Devices_Dao().Update(emp_id, dev_id)




        }


        fun InsertIntoPending(
            context: Context?,
            pendingDevices: Pending_Devices
        ) {
            data = initializeDb(context)
            data!!.All_Devices_Dao().insertIntoPending(pendingDevices)

        }

        fun getDetailsById(context: Context, id: String): Pending_Devices {
            data = initializeDb(context)

            return data!!.All_Devices_Dao().getDetailsByid1(id)
        }


        fun getPendingDevices(context: Context): LiveData<List<Pending_Devices>> {
            data = initializeDb(context)


            return data!!.All_Devices_Dao().getPendingDevices()
        }



        fun getDeviceDetailsById(context: Context,dev_id:String): LiveData<All_Devices_Entity> {
            data = initializeDb(context)

            return  data!!.All_Devices_Dao().getDetailsById(dev_id)
        }


    }
}