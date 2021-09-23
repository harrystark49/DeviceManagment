package com.example.mini_proect.fragments.emp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_emp_device_details.view.*

class emp_device_details : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var vi=inflater.inflate(R.layout.fragment_emp_device_details, container, false)



        return vi
    }

}