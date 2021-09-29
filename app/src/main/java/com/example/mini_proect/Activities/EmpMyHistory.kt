package com.example.mini_proect.Activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mini_proect.Adapter.myhistoryAdap
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_emp_my_history.*
import kotlinx.android.synthetic.main.fragment_emp_my_history.view.*

class EmpMyHistory : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_emp_my_history, container, false)

        var b = arguments
        var emp_id = b?.getString("empid",null)


        var list = All_Devices_view_Model().getAllDevicesHistory(this.requireContext(),emp_id!!)



        var lco = EmpMyHistory()
        var adap = myhistoryAdap(this.requireContext(),list,lco)
        view.HistoryRecycler.layoutManager = LinearLayoutManager(this.requireContext())
        view.HistoryRecycler.adapter = adap

        return view
    }
}
