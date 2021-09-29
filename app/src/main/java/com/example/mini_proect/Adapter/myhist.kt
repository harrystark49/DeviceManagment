package com.example.mini_proect.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_device__details.view.*
import kotlinx.android.synthetic.main.fragment_emp_myhistory.view.*
import kotlinx.android.synthetic.main.fragment_myhist.view.*
import kotlinx.android.synthetic.main.fragment_myhist.view.DUSTValue

class myhist : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_myhist, container, false)


        var b = arguments

        view.emp_device_id_value1.text = b?.getString("devId")
        view.emp_os_type_value1.text = b?.getString("ostype")
        view.emp_os_version_value1.text = b?.getString("osversion")

        view.emp_manufacture_value1.text = b?.getString("manfact")
        view.emp_phn_type_value1.text = b?.getString("phonetype")
        view.DUSTValue.text = b?.getString("startTime")
        view.statusvalue.text = b?.getString("endtime")


        return view
    }

}
