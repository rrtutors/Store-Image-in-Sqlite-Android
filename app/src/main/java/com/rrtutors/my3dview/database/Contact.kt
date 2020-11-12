package com.rrtutors.my3dview.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "Contact")
class Contact(@ColumnInfo(name = "name") var name:String,@ColumnInfo(name = "contact_number") var contact_number:String,@ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB) var image:ByteArray) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="_id")
    var id:Int=0 ;
}