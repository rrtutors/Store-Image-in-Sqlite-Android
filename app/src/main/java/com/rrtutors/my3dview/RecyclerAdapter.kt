package com.rrtutors.my3dview

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rrtutors.my3dview.database.Contact
import java.io.ByteArrayInputStream


class RecyclerAdapter (): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    var layoutiNflator:LayoutInflater?=null;
    var listContact:List<Contact>?=null
    constructor ( ctx:Context):this()
    {
        layoutiNflator= LayoutInflater.from(ctx)
        listContact= listOf()
    }

    fun addContactS(listContacts :List<Contact>)
    {
        listContact=listContacts;
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
       return RecyclerViewHolder(layoutiNflator!!.inflate(R.layout.contact_item,parent,false));
    }

    override fun getItemCount(): Int {

        return listContact!!.size;
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.text.text= listContact?.get(position)!!.name;
        holder.contact_number.text= listContact?.get(position)!!.contact_number;
        val imageStream = ByteArrayInputStream(listContact?.get(position)!!.image)
        val theImage = BitmapFactory.decodeStream(imageStream)
        holder.img.setImageBitmap(theImage)

    }
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val text:TextView=itemView.findViewById<TextView>(R.id.contact_name)
        val contact_number:TextView=itemView.findViewById<TextView>(R.id.contact_number)
        val img:ImageView=itemView.findViewById<ImageView>(R.id.contact_img)
    }
}