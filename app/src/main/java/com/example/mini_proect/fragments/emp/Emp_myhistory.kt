package com.example.mini_proect.fragments.emp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R


class emp_myhistory : Fragment() {
    lateinit var helper: dbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_emp_myhistory, container, false)
        helper = dbHelper(view.context)
        var db = helper.readableDatabase

        var args = arguments
        var emp_id = args?.getString("EmpEmail")




        return view
    }
}