package Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.mini_proect.R



class admin_Settings : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragments
        val view = inflater.inflate(R.layout.fragment_admin_settings, container, false)


//        view.update_profile.setOnClickListener {
//            val i = Intent(context,RegisterActivity::class.java)
//            startActivity(i)
//        }
//        view.change_password.setOnClickListener {
//            val i = Intent(context,ChangePassword::class.java)
//            startActivity(i)
//        }
        return view

    }
}
