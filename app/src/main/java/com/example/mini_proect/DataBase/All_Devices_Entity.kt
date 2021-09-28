package com.example.mini_proect.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_Devices_Details")
data class All_Devices_Entity(
    @PrimaryKey
    var device_Id:String,
    var phonetype:String,
    var Manufacture:String,
    var Version:String,
    var os_type:String
)
