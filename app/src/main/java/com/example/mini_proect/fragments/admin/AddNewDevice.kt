package com.example.mini_proect.fragments.admin

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.mini_proect.Activities.Home_screen_admin
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_add_new__device.*
import kotlinx.android.synthetic.main.fragment_add_new__device.view.*


class AddNewDevice : Fragment(), AdapterView.OnItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new__device, container, false)
        view.os_type_spinner.onItemSelectedListener = this
        view.phoneType_spinner.onItemSelectedListener = this
        var arr = arrayOf("IOS", "Android")
        var arr2 = arrayOf("Phone","Tablet")
        var adap =
            ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, arr)
        view.os_type_spinner.adapter = adap
        var adap2 = ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, arr2)
        view.phoneType_spinner.adapter = adap2
        return view
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}