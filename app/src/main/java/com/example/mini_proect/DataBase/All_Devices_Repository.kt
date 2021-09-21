package com.example.mini_proect.DataBase

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class All_Devices_Repository {

    companion object{

        var data:All_Devices_Db?=null
        var livedata:LiveData<All_Devices_Entity>?=null

        fun initializeDb(context: Context): All_Devices_Db {
            return All_Devices_Db.databaseclient(context)
        }

        fun insertData(context: Context,id:String,phn_type:String,maufacture:String,version:String) {

            data = initializeDb(context)

            CoroutineScope(Dispatchers.IO).launch {

                val loginDetails = All_Devices_Entity(id,phn_type,maufacture,version)
                data!!.All_Devices_Dao().insertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, id: String) : LiveData<All_Devices_Entity>? {

            data = initializeDb(context)

            livedata = data!!.All_Devices_Dao().getDetails(id)

            return livedata
        }
    }
}