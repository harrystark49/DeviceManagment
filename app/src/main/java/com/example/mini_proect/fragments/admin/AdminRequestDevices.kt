package com.example.mini_proect.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.R
import com.example.mini_proect.fragments.Adapter
import com.example.mini_proect.fragments.dataclass

class AdminRequestDevices : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_admin_request_devices, container, false)

        var list: ArrayList<dataclass> = arrayListOf()
        list.add(dataclass("asfdsfdgfjh","asdfdsg","adsfdsgfdh","aDSFDGF"))

        list.add(dataclass("asfdsfdgfjh","asdfdsg","adsfdsgfdh","aDSFDGF"))

        list.add(dataclass("asfdsfdgfjh","asdfdsg","adsfdsgfdh","aDSFDGF"))

        list.add(dataclass("asfdsfdgfjh","asdfdsg","adsfdsgfdh","aDSFDGF"))

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView2)

        var Adapte = adapter(list)

        recycler.adapter = Adapte
        return view
    }

}