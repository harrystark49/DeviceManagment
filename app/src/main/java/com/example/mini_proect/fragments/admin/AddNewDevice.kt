package com.example.mini_proect.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_add_new__device.*


class AddNewDevice : Fragment() {


    var  types = arrayOf("Android","IOS")
    val devices = arrayOf("Apple","Honor","Mi","Oppo","Pico","Samsung","Vivo")
    var spin: Spinner =spinnertype
    var spin2: Spinner =spinnerPhone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,types)
        spin.setAdapter(adapter)

        var adapter1 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,devices)
        spin2.setAdapter(adapter1)
    }


   fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        spin = p0 as Spinner
        spin2 = p0

        if(spin== spinnertype)
        {
            Toast.makeText(activity, "Selected type", Toast.LENGTH_SHORT).show()
        }
        else if(spin2==spinnerPhone)
        {
            Toast.makeText(activity, "Selected device"+type, Toast.LENGTH_SHORT).show()
        }
    }

     fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.mini_proect.R.layout.fragment_add_new__device, container, false)
    }


}