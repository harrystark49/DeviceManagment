package com.example.mini_proect.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(AllDevicesEntity::class),version = 2,exportSchema = false)
abstract class All_Devices_Db: RoomDatabase(){

    abstract fun All_Devices_Dao():AllDevicesDao
    companion object{
        @Volatile
        private var INSTACE:All_Devices_Db?=null

        fun databaseclient(context: Context):All_Devices_Db{
            if(INSTACE !=null)
                return INSTACE!!

            synchronized(context){
                INSTACE = Room
                    .databaseBuilder(context,All_Devices_Db::class.java,"emp_database")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTACE!!
            }
        }
    }


}