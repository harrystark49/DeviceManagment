package com.example.mini_proect.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_Devices_Details")
data class All_Devices_Entity(
    @PrimaryKey
    var device_Id:String,
    var phonetype:String,
    var Manufacture:String,
    var Version:String,
    var isAllocated:Boolean

)


@Entity(tableName = "Pending_Devices")
data class Pending_Devices(
    @PrimaryKey
    var device_Id:String,
    var phonetype:String,
    var Manufacture:String,
    var Version:String,
    val isAllocated:Boolean,
    var emp_name : String,
    var emp_number : String
)
