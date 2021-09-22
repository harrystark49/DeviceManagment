package com.example.mini_proect.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_Devices_Details")
data class All_Devices_Entity(
    @PrimaryKey
    val device_Id:String,
    val phonetype:String,
    val Manufacture:String,
    val Version:String

)
