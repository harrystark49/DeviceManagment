package com.example.mini_proect.DataBase

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class All_Devices_Repository {

    companion object {

        private var data: All_Devices_Db? = null
        private lateinit var livedata: LiveData<List<AllDevicesEntity>>
        private lateinit var ldata: LiveData<AllDevicesEntity>
        private lateinit var allDeviceslivedata: LiveData<List<AllDevicesEntity>>


        private fun initializeDb(context: Context?): All_Devices_Db {
            return All_Devices_Db.databaseclient(context!!)
        }

        fun insertData(
            context: Context,
            id: String,
            phn_type: String,
            maufacture: String,
            version: String,
            ostype:String
        ) {

            data = initializeDb(context)

            CoroutineScope(Dispatchers.IO).launch {

                val loginDetails = AllDevicesEntity(id, phn_type, maufacture, version,ostype)

                data!!.All_Devices_Dao().insertData(loginDetails)
            }

        }

        fun getLoginDetailsById(context: Context?, id: String): LiveData<AllDevicesEntity> {

            data = initializeDb(context)

            ldata = data!!.All_Devices_Dao().getDetailsById(id)

            return ldata
        }

        fun DeviceDetails(context: Context): LiveData<List<AllDevicesEntity>> {

            data = initializeDb(context)

            allDeviceslivedata = data!!.All_Devices_Dao().getDetails()

            return allDeviceslivedata
        }


        fun getLoginDetails(context: Context): LiveData<List<AllDevicesEntity>>? {

            data = initializeDb(context)

            livedata = data!!.All_Devices_Dao().getDetails()

            return livedata
        }

        fun delete(context: Context,id:String){

            data = initializeDb(context)
            data!!.All_Devices_Dao().delete(id)
        }
    }
}