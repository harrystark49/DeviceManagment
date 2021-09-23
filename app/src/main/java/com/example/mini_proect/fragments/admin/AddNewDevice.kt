package com.example.mini_proect.fragments.admin

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.Activities.Home_screen_admin
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import com.example.mini_proect.finish_Activity
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.fragment_add_new__device.*
import kotlinx.android.synthetic.main.fragment_add_new__device.view.*
import java.util.Arrays.asList
import kotlin.collections.ArrayList


class AddNewDevice : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var vm:All_Devices_view_Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new__device, container, false)


        view.add_new_device_button.setOnClickListener {
            vm=ViewModelProvider(this).get(All_Devices_view_Model::class.java)
            var devId = device_id.text.toString()
            var osVer = os_version.text.toString()
            var manu = Manufacture.text.toString()
            var phn_type=view.phoneType_spinner.selectedItem.toString()
            if (Error(devId, osVer, manu)) {
                val builder = AlertDialog.Builder(view.context)
                builder.setTitle(R.string.Devicemessage)
                    .setPositiveButton(getString(R.string.yes),
                        DialogInterface.OnClickListener { dialog, id ->
                            Toast.makeText(view.context, R.string.DeviceToast, Toast.LENGTH_SHORT)
                                .show()
                            vm.insertData(view.context,devId,phn_type,manu,osVer)
                            var intent = Intent(view.context, Home_screen_admin::class.java)
                            startActivity(intent)
                            activity?.finish()
                        })
                    .setNegativeButton(getString(R.string.no),
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                builder.create()
                builder.show()
            }
        }

        view.os_type_spinner.onItemSelectedListener = this
        view.phoneType_spinner.onItemSelectedListener

        var arr =
            arrayOf( getString(R.string.Android), getString(R.string.iOS))
        var arr2 = arrayOf(getString(R.string.Phone), getString(R.string.Tablet))

        var adap =
            ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, arr)
        view.os_type_spinner.adapter = adap

        var adap2 = ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, arr2)
        view.phoneType_spinner.adapter = adap2

        var manufactures = arrayOf(
            "Honor",
            "Samsung",
            "Oppo",
            "Realme",
            "RedMi",
            "Vivo",
            "One Plus+",
            "Nokia",
            "Motto",
            "Sony",
            "Lava",
            "Lenovo"
        )
        val adapter = ArrayAdapter(
            view.context,
            R.layout.list_view_items, manufactures
        )
        view.listview_1.adapter = adapter

        return view
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val text: String = os_type_spinner.selectedItem.toString()
        if (text == getString(R.string.Type)) {
            Manufacture.setText("")
            constraintLayout2.isVisible = false

        }

        if (text == getString(R.string.iOS)) {
            constraintLayout2.isVisible = false
            Manufacture.setText(getString(R.string.Apple))
            manufacture_layout.setEndIconOnClickListener {
                Manufacture.setText(getString(R.string.Apple))
                Toast.makeText(view?.context, R.string.OStype, Toast.LENGTH_SHORT).show()
                constraintLayout2.isVisible = false
            }
        }
        if (text == getString(R.string.Android)) {
            Manufacture.setText("")
            constraintLayout2.isVisible = true
            display()
            manufacture_layout.setEndIconOnClickListener {
                Manufacture.setText("")
                constraintLayout2.isVisible = true
                display()
            }
        }


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    private fun display() {
        listview_1.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var x = p0?.getItemAtPosition(p2).toString()
                Toast.makeText(view?.context, "$x", Toast.LENGTH_SHORT).show()
                if (constraintLayout2.isVisible == true) {
                    val itemvalue = listview_1.getItemAtPosition(p2) as String
                    Manufacture.setText(itemvalue)
                    constraintLayout2.isVisible = false
                }
            }

        }

    }

    private fun Error(deviceId: String, osVersion: String, manufacture: String): Boolean {
        var y = 0
        if (deviceId.isEmpty()) {
            deviceID.error = getString(R.string.EnterDeviceID)
            deviceID.isErrorEnabled = true
        } else {
            deviceID.isErrorEnabled = false
            y++
        }
        if (osVersion.isEmpty()) {
            os_version_layout.error = getString(R.string.EnterOSVersion)
            os_version_layout.isErrorEnabled = true
        } else {
            os_version_layout.isErrorEnabled = false
            y++
        }
        if (manufacture.isEmpty()) {
            manufacture_layout.error = getString(R.string.SelectManufacture)
            manufacture_layout.isErrorEnabled = true
        } else {
            manufacture_layout.isErrorEnabled = false
            y++
        }
        if (y == 3) {
            return true
        }
        return false
    }

}
