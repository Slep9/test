package com.example.marvel.bd

import androidx.room.*
import com.example.marvel.data.marvel.Result

@Dao
interface ResultDao {
    @Query("SELECT * FROM result")
    fun getAll(): List<Result>

    @Query("SELECT * FROM result WHERE id = :idPrimaryKey")
    fun getById(idPrimaryKey: Long): Result

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: Result)

    @Update
    fun update(result: Result);

    @Delete
    fun delete(result: Result);
}


