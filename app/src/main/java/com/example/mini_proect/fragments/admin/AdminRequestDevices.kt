package com.example.mini_proect.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.Adapter.PendingDevicesAdapter
import com.example.mini_proect.R

class AdminRequestDevices : Fragment() {

    lateinit var ViewModel: All_Devices_view_Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_admin_request_devices, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView2)

        ViewModel = ViewModelProvider(this).get(All_Devices_view_Model::class.java)

        ViewModel.getPendingDevices(this.requireContext())!!.observe(requireActivity(), Observer {

            if (it.isNotEmpty()) {
                var adapter = PendingDevicesAdapter(requireContext(), it)
                recycler.adapter = adapter
            }else{
                Toast.makeText(requireContext(), "No Data Found of Pending Devices!!", Toast.LENGTH_SHORT).show()
            }
        })



        return view
    }

}