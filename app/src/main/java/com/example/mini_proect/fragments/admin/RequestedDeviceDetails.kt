package com.example.mini_proect.fragments.admin

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.mini_proect.DataBase.DBHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.all_devices
import com.example.mini_proect.fragments.emp.MyDevices
import kotlinx.android.synthetic.main.fragment_requested_device_details.view.*
import java.text.SimpleDateFormat
import java.util.*


class requested_device_details : Fragment() {
    lateinit var helper: DBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.emp_fragment_replacer, MyDevices())
                    commit()
                }
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(callback)
        var view = inflater.inflate(R.layout.fragment_requested_device_details, container, false)
        helper = DBHelper(view.context)
        var db = helper.readableDatabase
        var args = arguments
        var id_device = args?.get("DeviceId").toString()

        var cv = ContentValues()
        var gmail = ""
        var cursor = db.rawQuery(
            "SELECT * FROM REQUESTED_DEVICES WHERE DEVICE_ID=?",
            arrayOf(id_device)
        )

        if (cursor.moveToNext()) {

            var gmailid = cursor.getColumnIndex("EMAIL")
            gmail = cursor.getString(gmailid)

            var db_id = cursor.getColumnIndex("DEVICE_ID")
            var idd = cursor.getString(db_id)

            var manufacture_db = cursor.getColumnIndex("MANUFACTURE")
            var manufacture = cursor.getString(manufacture_db)

            var OS_TYPE_db = cursor.getColumnIndex("OS_TYPE")
            var OS_TYPE = cursor.getString(OS_TYPE_db)

            var VERSION_db = cursor.getColumnIndex("VERSION")
            var VERSION = cursor.getString(VERSION_db)

            var PHN_TYPE_db = cursor.getColumnIndex("PHN_TYPE")
            var PHN_TYPE = cursor.getString(PHN_TYPE_db)

            cv.put("DEVICE_ID", idd)
            view.emp_device_id_value2.setText(idd)
            view.emp_os_type_value2.setText(OS_TYPE)
            view.emp_os_version_value2.setText(VERSION)
            view.emp_manufacture_value2.setText(manufacture)
            view.emp_phn_type_value2.setText(PHN_TYPE)

        }

        var cursor1 = db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", arrayOf(gmail))
        if (cursor1.moveToNext()) {

            var name_db = cursor1.getColumnIndex("NAME")
            var name = cursor1.getString(name_db)

            var empid_db = cursor1.getColumnIndex("ID")
            var id = cursor1.getString(empid_db)

            var numberdb = cursor1.getColumnIndex("MOBILE")
            var number = cursor1.getString(numberdb)

            cv.put("EMP_ID", id)
            view.device_used_value.setText(name)
            view.empID_value.setText(id)
            view.mobile_number_value.setText(number)

            var SimpleDataFormat = SimpleDateFormat("MM.dd.yyyy HH:mm:ss")

            view.accept.setOnClickListener {
                var date = SimpleDataFormat.format(Date())
                cv.put("START_TIME", date)
                db.insert("ACCEPTED_DEVICES", null, cv)

                db.delete("REQUESTED_DEVICES", "DEVICE_ID=?", arrayOf(id))
                view.accept.setText("Accepted")
                view.decline.visibility = View.GONE
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.fragment_replacer, all_devices("Admin", ""))
                    commit()
                    db.delete("REQUESTED_DEVICES", "DEVICE_ID=?", arrayOf(id_device))
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.fragment_replacer, all_devices("Admin", ""))
                        commit()
                    }
                    Toast.makeText(context, "Device added to user", Toast.LENGTH_SHORT).show()
                }
            }


        }

        view.decline.setOnClickListener {
            db.delete("REQUESTED_DEVICES", "DEVICE_ID=?", arrayOf(id_device))
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_replacer, all_devices("Admin", ""))
                commit()
            }
        }
        return view
    }


}