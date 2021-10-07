package com.example.mini_proect.fragments.emp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.DBHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.Device_Details
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.*
import java.text.SimpleDateFormat
import java.util.*

class EmpDeviceDetails(var email: String, var details: String = "details") : Fragment() {

    lateinit var helper: DBHelper
    lateinit var loginViewModel: All_Devices_view_Model

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var vi = inflater.inflate(R.layout.fragment_emp_device_details, container, false)
        helper = DBHelper(vi.context)
        var db = helper.readableDatabase

        var args = arguments
        var id = args?.getString("DeviceId")
        var mail = args?.getString("Email")

        var c1 = db.rawQuery("SELECT * FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))
        var c2 = db.rawQuery("SELECT * FROM ACCEPTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))

        var mail1 = ""
        var mail2 = ""
        var mail3 = ""
        if (c1.moveToFirst()) {
            mail1 = c1.getString(c1.getColumnIndex("EMAIL"))

        }
        if (c2.moveToFirst()) {
            mail2 = c2.getString(c2.getColumnIndex("EMP_ID"))

            var c3 = db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE ID=?", arrayOf(mail2))


            if (c3.moveToFirst()) {
                mail3 = c3.getString(c3.getColumnIndex("EMAIL"))
            }
        }

        var b = Bundle()
        b.putString("DeviceId", id)
        b.putString("Email", mail)
        var frag = Device_Details()
        frag.arguments = b
        if (mail3.isNotEmpty() || mail1.isNotEmpty()) {

            if ((mail != mail3) && (mail != mail1)) {

                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.emp_fragment_replacer, frag)
                    commit()
                }
            }
        }



        loginViewModel = ViewModelProvider(this).get(All_Devices_view_Model::class.java)

        loginViewModel.getLoginDetailsById(context, id.toString())!!
            .observe(this.viewLifecycleOwner, Observer {

                if (it != null) {
                    vi.emp_device_id_value1.setText(it.device_Id)
                    vi.emp_os_type_value1.setText(it.os_type)
                    vi.emp_manufacture_value1.setText(it.Manufacture)
                    vi.emp_os_version_value1.setText(it.Version)
                    vi.emp_phn_type_value1.setText(it.phonetype)
                } else {
                    Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()
                }
            })

        var cursor =
            db.rawQuery("SELECT DEVICE_ID FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))
        var cursor1 =
            db.rawQuery("SELECT DEVICE_ID FROM ACCEPTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))
        if (cursor != null && cursor.moveToNext()) {
            vi.register_device.visibility = View.VISIBLE
            vi.register_device.setText("STATUS PENDING")
        } else if (cursor1.moveToNext()) {
            vi.register_device.visibility = View.VISIBLE
            vi.register_device.setText("RETURN DEVICE")
        } else {
            vi.register_device.visibility = View.VISIBLE
            vi.register_device.setText("REGISTER DEVICE")
        }

        var reg = vi.register_device.text

        vi.register_device.setOnClickListener {
            if (reg == "REGISTER DEVICE") {
                loginViewModel.getLoginDetailsById(context, id.toString())!!
                    .observe(this.viewLifecycleOwner, Observer {
                        var cv = ContentValues()
                        if (it != null) {
                            cv.put("DEVICE_ID", it.device_Id)
                            cv.put("MANUFACTURE", it.Manufacture)
                            cv.put("VERSION", it.Version)
                            cv.put("PHN_TYPE", it.phonetype)
                            cv.put("OS_TYPE", it.os_type)
                            cv.put("EMAIL", email)
                            db.insert("REQUESTED_DEVICES", null, cv)
                            vi.register_device.setText("STATUS PENDING")
                        } else {
                            Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()
                        }
                    })
            } else if (reg == "RETURN DEVICE") {
                var cv1 = ContentValues()
                cv1.put("DEVICE_ID", id)
                var cv2=db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", arrayOf(mail))
                var emp_id=""
                if(cv2.moveToNext()){
                    emp_id=cv2.getString(cv2.getColumnIndex("ID"))
                }
                cv1.put("EMP_ID", emp_id)


                val simpleDateFormat = SimpleDateFormat("MM.dd.yyyy HH:mm:ss")
                val currentDateAndTime: String = simpleDateFormat.format(Date())
                cv1.put("END_TIME", currentDateAndTime)

                var cursor = db.rawQuery(
                    "SELECT START_TIME FROM ACCEPTED_DEVICES WHERE DEVICE_ID=?",
                    arrayOf(id)
                )
                if (cursor.moveToFirst()) {
                    var time = cursor.getString(cursor.getColumnIndex("START_TIME")).toString()
                    cv1.put("START_TIME", time)
                }
                vi.register_device.setText("REGISTER DEVICE")
                Toast.makeText(vi.context, "Succesfully Returned", Toast.LENGTH_SHORT).show()


                var c = db.rawQuery(
                    "SELECT * FROM DEVICE_HISTORY WHERE DEVICE_ID=? AND EMP_ID=?",
                    arrayOf(id, emp_id)
                )
                if (c.moveToNext()) {
                    db.update("DEVICE_HISTORY", cv1, "DEVICE_ID=? AND EMP_ID=?", arrayOf(id, emp_id))
                } else {
                    db.insert("DEVICE_HISTORY", null, cv1)
                }




                db.delete("ACCEPTED_DEVICES", "DEVICE_ID=?", arrayOf(id))
            } else {
                var cursor =
                    db.rawQuery("SELECT * FROM REQUESTED_DEVICES WHERE DEVICE_ID=?", arrayOf(id))
                var cursor1 = db.rawQuery(
                    "SELECT START_TIME FROM ACCEPTED_DEVICES WHERE DEVICE_ID=?",
                    arrayOf(id)
                )
                if (!(cursor.moveToNext() || cursor1.moveToNext())) {
                    vi.register_device.setText("REGISTER DEVICE")
                }
            }
        }


        return vi
    }

}