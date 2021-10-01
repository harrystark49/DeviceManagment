package com.example.mini_proect.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao()
interface AllDevicesDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertData(AllDevicesEntity:AllDevicesEntity)


    @Query("SELECT * FROM all_Devices_Details WHERE device_Id=:id")
    fun getDetailsById(id:String): LiveData<AllDevicesEntity>

    @Query("SELECT * FROM all_Devices_Details ORDER BY device_Id")
    fun getDetails(): LiveData<List<AllDevicesEntity>>



}