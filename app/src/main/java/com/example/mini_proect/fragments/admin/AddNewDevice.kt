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


class AddNewDevice : Fragment(), AdapterView.OnItemSelectedListener {


    var  types = arrayOf("Android","IOS")
    val devices = arrayOf("Apple","Honor","Mi","Oppo","Pico","Samsung","Vivo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var spin = spinnertype
        val adapter = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_item,
                types
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adapter
        spin.onItemSelectedListener = this

        spinnerPhone.onItemSelectedListener
        var adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,devices)
        spinnerPhone.adapter=adapter1

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val spinner = p0
        val spinner2 = p0

        if(spinner== spin)
        {
            Toast.makeText(activity, "Selected type", Toast.LENGTH_SHORT).show()
        }
        else if(spinner2==spinnerPhone)
        {
            Toast.makeText(activity, "Selected device"+type, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new__device, container, false)
    }

}