package com.rrtutors.my3dview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rrtutors.my3dview.database.Contact
import com.rrtutors.my3dview.database.MyDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listContact:List<Contact>?=null
    var adapter:RecyclerAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contact_list.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        listContact=MyDatabase.getInstance(applicationContext)!!.contactsDAO().fetchContacts()

        adapter=RecyclerAdapter(applicationContext)
        contact_list.adapter=adapter
        if(listContact!=null)
            adapter!!.addContactS(listContact!!)

        add_contact.setOnClickListener {
            startActivityForResult(Intent(applicationContext,AddContact::class.java),200)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==200&&resultCode==200)
        {
            listContact=MyDatabase.getInstance(applicationContext)!!.contactsDAO().fetchContacts()
            if(listContact!=null)
                adapter!!.addContactS(listContact!!)
        }
    }
}