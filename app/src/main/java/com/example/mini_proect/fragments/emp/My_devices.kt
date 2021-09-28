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
import com.example.mini_proect.Adapter.Request_device_Adapter
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import com.example.mini_proect.fragments.Adapter


class My_devices : Fragment() {
    lateinit var helper: dbHelper
    private lateinit var viewModel: All_Devices_view_Model

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_my_devices, container, false)

        var list=ArrayList<All_Devices_Entity>()

        helper=dbHelper(view.context)
        var db=helper.readableDatabase

        var idlist=ArrayList<String>()
        var id=""
        var args=arguments
        var mail=args?.getString("EmpEmail")

        var cursor=db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", arrayOf(mail))
        if(cursor.moveToFirst()){
             id=cursor.getString(cursor.getColumnIndex("ID"))
        }
        Toast.makeText(view.context, "$id", Toast.LENGTH_SHORT).show()

        var cursor1=db.rawQuery("SELECT * FROM ACCEPTED_DEVICES WHERE EMP_ID=?", arrayOf(id))

        while(cursor1.moveToNext()){
            Toast.makeText(view.context, "asdfkl", Toast.LENGTH_SHORT).show()
            var id=cursor1.getString(cursor1.getColumnIndex("DEVICE_ID"))
            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
            ).get(All_Devices_view_Model::class.java)
            viewModel.getLoginDetailsById(requireContext(),id)!!.observe(requireActivity(), Observer {
                list.add(it)
                val recyc = view.findViewById<RecyclerView>(R.id.recyclerView3)
                var recycle: RecyclerView = recyc
                var LLM: LinearLayoutManager = LinearLayoutManager(context)
                LLM.orientation = RecyclerView.VERTICAL
                recycle.layoutManager = LLM
                var adapter =  Adapter(view.context,list,"Admin",mail.toString())
                recycle.adapter = adapter
            })
        }



        return  view
    }
}