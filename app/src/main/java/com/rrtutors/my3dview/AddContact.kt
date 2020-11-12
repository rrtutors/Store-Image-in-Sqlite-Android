package com.rrtutors.my3dview


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.rrtutors.my3dview.database.Contact
import com.rrtutors.my3dview.database.MyDatabase
import kotlinx.android.synthetic.main.activity_add_contact.*

import java.io.ByteArrayOutputStream


class AddContact : AppCompatActivity() {
    var name:String?=null;
    var number:String?=null;
    val REQUEST_CODE_PICKER:Int=201
    var imagePath:String?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        name= contact_name.text.toString()
        number= contact_number.text.toString()
        img_add.setOnClickListener {
            ImagePicker.create(this) // Activity or Fragment
                .start(REQUEST_CODE_PICKER);
        }

        add_contact.setOnClickListener {
            val image = BitmapFactory.decodeFile(imagePath
            )
            name=contact_name.text.toString()
            number=contact_number.text.toString()
            if(name!!.isEmpty())
            {
                Toast.makeText(applicationContext,"Please enter Name",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(number!!.isEmpty())
            {
                Toast.makeText(applicationContext,"Please enter number",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(imagePath==null||imagePath!!.isEmpty())
            {
                Toast.makeText(applicationContext,"Please select Contact Image",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
// convert bitmap to byte
// convert bitmap to byte
            val stream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val imageInByte: ByteArray = stream.toByteArray()

            MyDatabase.instance!!.contactsDAO().insert(Contact(name!!, number!!,imageInByte))
            setResult(200)
            finish()
        }
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICKER && resultCode == Activity.RESULT_OK && data != null) {
            val images =
                ImagePicker.getImages(data) as ArrayList<Image>
            imagePath=images[0].path;
            img_contact.setImageURI(Uri.parse(images[0].path))

        }
    }
}