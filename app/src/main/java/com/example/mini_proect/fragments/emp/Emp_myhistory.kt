package com.example.mini_proect.fragments.emp

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.Adapter.adap
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R


class emp_myhistory : Fragment() {
    lateinit var helper: dbHelper
    private lateinit var viewModel: All_Devices_view_Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view=inflater.inflate(R.layout.fragment_my_devices, container, false)

        val b = arguments
        val email = b!!.getString("EmpEmail")
        var list=ArrayList<All_Devices_Entity>()

        helper=dbHelper(view.context)
        var db=helper.readableDatabase

        var id=""

        var cursor=db.rawQuery("SELECT * FROM DEVICE_HISTORY WHERE EMP_MAIL=?", arrayOf(email))

        while(cursor.moveToNext()){

            var id=cursor.getString(cursor.getColumnIndex("DEVICE_ID"))

            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
            ).get(All_Devices_view_Model::class.java)
            viewModel.getLoginDetailsById(requireContext(),id)!!.observe(requireActivity(), Observer {
                list.add(it)
                val recyc = view.findViewById<RecyclerView>(R.id.recyclerView3)
                var recycle: RecyclerView = recyc
                var LLM = LinearLayoutManager(context)
                LLM.orientation = RecyclerView.VERTICAL
                recycle.layoutManager = LLM
                var adapter =  adap(view.context,list,email.toString())
                recycle.adapter = adapter
            })
        }

        var view= inflater.inflate(R.layout.fragment_my_devices, container, false)

        var list=ArrayList<All_Devices_Entity>()

        helper=dbHelper(view.context)
        var db=helper.readableDatabase

        var id=""
        var args=arguments
        var mail=args?.getString("EmpEmail")

        var cursor=db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", arrayOf(mail))
        if(cursor.moveToFirst()){

            id=cursor.getString(cursor.getColumnIndex("ID"))
        }


        var cursor1=db.rawQuery("SELECT * FROM DEVICE_HISTORY WHERE EMP_ID=?", arrayOf(id))

        while(cursor1.moveToNext()){

            var idd=cursor1.getString(cursor1.getColumnIndex("DEVICE_ID"))

            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
            ).get(All_Devices_view_Model::class.java)
            viewModel.getLoginDetailsById(requireContext(),idd)!!.observe(requireActivity(), Observer {
                list.add(it)
                val recyc = view.findViewById<RecyclerView>(R.id.recyclerView3)
                var recycle: RecyclerView = recyc
                var LLM: LinearLayoutManager = LinearLayoutManager(context)
                LLM.orientation = RecyclerView.VERTICAL
                recycle.layoutManager = LLM
                var adapter =  adap(view.context,list,true,mail.toString())
                recycle.adapter = adapter
            })
        }
        return  view
    }
}