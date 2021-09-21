package com.example.mini_proect.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.alldeviceviewitem.*
import kotlinx.android.synthetic.main.fragment_add_new__device.*
import kotlinx.android.synthetic.main.fragment_add_new__device.view.*


class AddNewDevice : Fragment(), AdapterView.OnItemSelectedListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new__device, container, false)
        // Inflate the layout for this fragment
        var types = arrayOf("Android", "IOS")
        val devices = arrayOf("Phone","Tablet")
        view.spinnertype.onItemSelectedListener = this
        view.spinnerPhone.onItemSelectedListener = this

        var adapter1 = ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, devices)
        view.spinnerPhone.adapter = adapter1
        var adp = ArrayAdapter(view.context,R.layout.support_simple_spinner_dropdown_item, types)
        view.spinnertype.adapter = adp
        return view
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}