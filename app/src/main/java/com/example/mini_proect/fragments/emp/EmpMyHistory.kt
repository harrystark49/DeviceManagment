package com.example.mini_proect.fragments.emp

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mini_proect.Adapter.adap
import com.example.mini_proect.DataBase.AllDevicesEntity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.DataBase.DBHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_my_devices.view.*
import kotlin.math.log


class emp_myhistory : Fragment() {
    lateinit var helper: DBHelper
    private lateinit var viewModel: All_Devices_view_Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view= inflater.inflate(R.layout.fragment_my_devices, container, false)

        var list=ArrayList<AllDevicesEntity>()

        helper=DBHelper(view.context)
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
                if(it==null){
                  view.nodevice.visibility=View.VISIBLE
                }else{
                    view.nodevice.visibility=View.GONE
                    list.add(it)

                    val recyc = view.findViewById<RecyclerView>(R.id.recyclerView3)
                    var recycle: RecyclerView = recyc
                    var LLM = LinearLayoutManager(context)
                    LLM.orientation = RecyclerView.VERTICAL
                    recycle.layoutManager = LLM
                    var adapter =  adap(view.context,list,true,mail.toString())
                    recycle.adapter = adapter
                }
            })
        }
        Log.d("DEBUG","$list")
        return  view
    }
}