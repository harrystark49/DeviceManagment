package com.example.mini_proect.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.R
import com.example.mini_proect.fragments.Adapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    private lateinit var viewModel: All_Devices_view_Model
    lateinit var devicesList: List<All_Devices_Entity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        project_title.animate().setDuration(1000).rotationYBy(360f)
        app_title.alpha = 0f
        app_title.animate().setDuration(3000).alpha(1f).withEndAction {

            var intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }



        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(All_Devices_view_Model::class.java)

        viewModel.insertData(this, "1", "Nokia", "Android", "Mini")
        viewModel.insertData(this, "2", "Samsung", "Android", "Phone")
        viewModel.insertData(this, "3", "Huweii", "Andoird", "Tablet")
        viewModel.insertData(this, "4", "Redmi", "Android", "Book")
        viewModel.insertData(this, "5", "Apple13", "IOS", "Phone")


        viewModel.getLoginDetails(this)?.observe(this, Observer {
            if(it.isNotEmpty()){
            devicesList = it
            Adapter(this,devicesList)
        }else{
            Toast.makeText(this,"No Device Available",Toast.LENGTH_SHORT).show()
            }
        })


    }
}









