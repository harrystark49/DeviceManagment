package com.example.mini_proect.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mini_proect.Activities.ChangePassword
import com.example.mini_proect.Activities.Register
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.fragment_admin_settings.*
import kotlinx.android.synthetic.main.fragment_emp_settings.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [emp_settings.newInstance] factory method to
 * create an instance of this fragment.
 */
class emp_settings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       val view=  inflater.inflate(R.layout.fragment_emp_settings, container, false)
       view.emp_update_profile.setOnClickListener {
           val intent= Intent(context,Register::class.java)
           startActivity(intent)
       }
        view.emp_change_password.setOnClickListener {
            val intent = Intent(context,ChangePassword::class.java)
            startActivity(intent)
        }
        view.admin
        return view
    }

}
