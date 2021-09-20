package com.example.mini_proect.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbHelper(context: Context):SQLiteOpenHelper(context,"myDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ADD_EMPLOYEE(ID VARCHAR2 PRIMARY KEY,NAME VARCHAR2,EMAIL VARCHAR2,PASSWORD VARCAHR2,MOBILE VARCHAR2)")
        db?.execSQL("CREATE TABLE ADD_ADMIN(ID VARCHAR2 PRIMARY KEY,NAME VARCHAR2,EMAIL VARCHAR2,PASSWORD VARCAHR2,MOBILE VARCHAR2)")
        db
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}