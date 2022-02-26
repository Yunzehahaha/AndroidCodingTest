package com.example.jetcomposedemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "record")
class Record {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int = 0

    @ColumnInfo(name = "volume")
    @SerializedName("volume_of_mobile_data")
    val volumeOfMobileData: Double = 0.00

    @ColumnInfo(name = "quarter")
    @SerializedName("quarter")
    val quarter : String = ""

}