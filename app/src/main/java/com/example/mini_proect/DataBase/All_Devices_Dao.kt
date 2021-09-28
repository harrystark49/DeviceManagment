package com.example.mini_proect.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao()
interface All_Devices_Dao {



      @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertData(All_Devices_Entity:All_Devices_Entity)


    @Query("SELECT * FROM all_Devices_Details WHERE device_Id=:id")
    fun getDetailsById(id:String): LiveData<All_Devices_Entity>

    @Query("SELECT * FROM all_Devices_Details ORDER BY device_Id")
    fun getDetails(): LiveData<List<All_Devices_Entity>>




}