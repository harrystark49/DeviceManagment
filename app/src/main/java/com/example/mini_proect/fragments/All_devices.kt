package com.example.mini_proect.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.R


class all_devices : Fragment() {
    var Devices: ArrayList<dataclass> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_all_devices, container, false)

        initRecycler()
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        var recycle: RecyclerView = recycler
        var LLM: LinearLayoutManager = LinearLayoutManager(context)
        LLM.orientation = RecyclerView.VERTICAL
        recycle.layoutManager = LLM
        var adapter = Adapter(Devices)
        recycle.adapter = adapter


        return view
    }


    private fun initRecycler() {
        Devices.add(dataclass("Trychup Herbal", "hcvjd", "hjfqe", "gqfqjfq"))
        Devices.add(dataclass("Trychup Herbal", "hcvjd", "hjfqe", "gqfqjfq"))
        Devices.add(dataclass("Trychup Herbal", "hcvjd", "hjfqe", "gqfqjfq"))
        Devices.add(dataclass("Trychup Herbal", "hcvjd", "hjfqe", "gqfqjfq"))

    }


}