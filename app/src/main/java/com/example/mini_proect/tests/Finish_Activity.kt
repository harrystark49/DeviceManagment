package com.example.mini_proect

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object finish_Activity {
    public fun close_Activity(context: Context){
        var builder= AlertDialog.Builder(context)
        builder.setTitle("Do you want exit the Inventory app?")
        builder.setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
           // context.finish()
        }
        builder.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->

        }
        builder.create()
        builder.setCancelable(true)
        builder.show()
    }
}