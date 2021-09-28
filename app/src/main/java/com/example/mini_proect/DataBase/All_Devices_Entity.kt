package com.example.mini_proect.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_Devices_Details")
data class All_Devices_Entity(
    @PrimaryKey
    var device_Id: String,
    var phonetype: String,
    var OsType: String,
    var Manufacture: String,
    var Version: String,
    var isAllocated: String,
    )


@Entity(tableName = "Pending_Devices")
data class Pending_Devices(
    @PrimaryKey
    var device_Id: String,
    var emp_id: String?
)


@Entity(tableName = "History")
data class History(

    var device_Id: String,
    var emp_id: String,
    @PrimaryKey
    var startTme:String,
    var EndTime:String
)
