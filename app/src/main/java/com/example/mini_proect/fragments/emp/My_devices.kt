package com.example.mini_proect.fragments.emp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mini_proect.Adapter.MydevAdapter
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_my_devices.view.*


class My_devices() : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var sharedPreferences = activity?.getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)

        var mail1 = sharedPreferences?.getString("email", null)
        var id1 = sharedPreferences?.getString("empid", null)
        Log.d("DEBUG", "${mail1},${id1}")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_my_devices, container, false)

        var sharedPreferences = activity?.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

        var emp_id = sharedPreferences!!.getString("empid", null)


        var b: Bundle? = arguments
        var text = b?.getString("text")



        if (emp_id != null) {
            All_Devices_view_Model().getMyDevices(this.requireContext(), emp_id)
                .observe(this.viewLifecycleOwner,
                    Observer {
                        val adapter = MydevAdapter(this.requireContext(), it, text)
                        view.my_devices_recyclerview.layoutManager =
                            LinearLayoutManager(this.requireContext())
                        view.my_devices_recyclerview.adapter = adapter

                    })
        } else {
            Toast.makeText(this.requireContext(), "No EmpId Found", Toast.LENGTH_SHORT).show()
        }


        return view
    }
}