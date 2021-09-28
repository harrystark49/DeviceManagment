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



    @Query("UPDATE all_Devices_Details set isAllocated = :emp_id where device_Id = :dev_id")
    fun Update(emp_id: String?, dev_id:String)





    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertIntoPending(Pending_devices: Pending_Devices)


    @Query("SELECT * FROM Pending_Devices where device_Id=:id")
    fun getDetailsByid1(id:String) : Pending_Devices

    @Query("SELECT * FROM Pending_Devices")
    fun getPendingDevices() : LiveData<List<Pending_Devices>>


    @Query("SELECT * FROM all_Devices_Details where isAllocated=:emp_id")
    fun getMyDevices(emp_id: String) : LiveData<List<All_Devices_Entity>>



    @Query("DELETE FROM Pending_Devices WHERE device_Id=:dev_id")
    fun RemoveFromPendingDevices(dev_id: String)

    //Both the Queries Are Same!!!!
    @Query("DELETE FROM  pending_devices where device_Id = :dev_id")
    fun deletepending(dev_id: String)



    @Query("INSERT INTO History (device_Id, emp_id, startTme,EndTime) VALUES (:dev_id, :eemp_id,:time,:endtime)")
    fun UpdateStartTime(eemp_id:String,dev_id:String,time:String,endtime:String)


    @Query("Update History set EndTime=:endtime where device_Id=:dev_id AND emp_id=:emp_id")
    fun UpdateEndTime(emp_id: String,dev_id: String,endtime:String)

    @Query("SELECT * from History  where device_Id=:dev_id")
    fun getHistory(dev_id: String):History

}






// @Query("INSERT INTO pending_devices(device_Id,emp_name,emp_number) VALUES(:device_id,:emp_name,:emp_num) ")
