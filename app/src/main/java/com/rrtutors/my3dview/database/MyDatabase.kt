package com.rrtutors.my3dview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room



import androidx.room.RoomDatabase

@Database(entities = arrayOf(Contact::class),version = 1,exportSchema = false)
abstract  class MyDatabase:RoomDatabase() {
    companion object{
        var instance:MyDatabase?=null;
        fun getInstance(ctx: Context):MyDatabase
        {
            if(instance!=null)
            {
                return  instance as MyDatabase;
            }

            instance= Room.databaseBuilder(ctx,MyDatabase::class.java,"mydb").run {
                allowMainThreadQueries()
            }.build()
            return instance as MyDatabase;
        }
    }
    abstract fun contactsDAO():ContactsDAO;
}