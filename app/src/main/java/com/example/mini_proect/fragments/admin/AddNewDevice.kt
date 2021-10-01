package com.example.mini_proect.fragments.admin
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.Activities.Home_screen_admin
import com.example.mini_proect.DataBase.AllDevicesEntity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_add_new__device.*
import kotlinx.android.synthetic.main.fragment_add_new__device.view.*


class AddNewDevice : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var vm: All_Devices_view_Model
    lateinit var adap3:ArrayAdapter<String>
    lateinit var adap4:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new__device, container, false)


        view.add_new_device_button.setOnClickListener {

            vm = ViewModelProvider(this).get(All_Devices_view_Model::class.java)
            var devId = device_id.text.toString()

            var osVer = os_version.text.toString()
            var os_type = os_type_spinner.selectedItem.toString()
            var phn_type = view.phoneType_spinner.selectedItem.toString()
            var manuf = view.manufacture_spinner.selectedItem.toString()


    vm.getLoginDetailsById(view.context, devId)?.observe(viewLifecycleOwner, Observer {
        if(it == null){

            deviceID.isErrorEnabled = false
                if (Error(devId, osVer)) {
                    val builder = AlertDialog.Builder(view.context)
                    builder.setTitle(R.string.Devicemessage)
                        .setPositiveButton(getString(R.string.yes),
                            DialogInterface.OnClickListener { dialog, id ->
                                Toast.makeText(
                                    view.context,
                                    R.string.DeviceToast,
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                vm.insertData(view.context, devId, phn_type, osVer, os_type, manuf)
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
        }else{
            deviceID.error = "Device already exists"
            deviceID.isErrorEnabled = true
        }
    })


        }

        view.os_type_spinner.onItemSelectedListener = this
        view.phoneType_spinner.onItemSelectedListener

        var arr =
            arrayOf(getString(R.string.Android), getString(R.string.iOS))
        var arr2 = arrayOf(getString(R.string.Phone), getString(R.string.Tablet))

        var adap =
            ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, arr)
        view.os_type_spinner.adapter = adap

        var adap2 = ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, arr2)
        view.phoneType_spinner.adapter = adap2

        var manufactures = arrayOf(
            "Samsung",
            "Oppo",
            "Realme",
            "RedMi",
            "Vivo",
            "One Plus+",
            "Nokia",

        )
        adap3 =
            ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, manufactures)
        view.manufacture_spinner.adapter = adap3

        adap4= ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, arrayOf("Apple"))

        return view
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        if(p0?.selectedItem=="iOS"){
            manufacture_spinner.adapter = adap4

        }else{
           manufacture_spinner.adapter = adap3
            manufacture_spinner.setSelection(0)

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun Error(deviceId: String, osVersion: String): Boolean {
        var y = 0
        if (deviceId.isEmpty()) {
            deviceID.error = getString(R.string.EnterDeviceID)
            deviceID.isErrorEnabled = true
        } else if (deviceId.length<3){
            deviceID.error = "Device Id must be of minimum length 3"
            deviceID.isErrorEnabled = true

        }else {
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
        if(y==2){
            return true
        }
        return false
    }
}