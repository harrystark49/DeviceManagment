package com.example.mini_proect.fragments.emp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_emp_myhistory.view.*


class emp_myhistory(var list: ArrayList<String>) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_emp_myhistory, container, false)

        view.emp_device_id_value.text = list[0]
        view.emp_manufacture_value.text = list[1]
        view.emp_phn_type_value.text = list[2]
        view.emp_os_type_value.text = list[3]
        view.emp_os_version_value.text = list[4]
        view.DUSTValue.text = list[5]
        view.DUETValue.text = list[6]
        list.clear()

        return view
    }
}
