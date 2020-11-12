package com.rrtutors.my3dview.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(registration:Contact)

    @Query("select * from Contact ")
    fun fetchContacts():List<Contact>;
}