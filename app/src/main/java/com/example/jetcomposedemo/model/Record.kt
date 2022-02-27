package com.example.jetcomposedemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "record")
data class Record(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("_id")
    var id: Int = 0,

    @ColumnInfo(name = "volume")
    @SerializedName("volume_of_mobile_data")
    var volumeOfMobileData: Double = 0.00,

    @ColumnInfo(name = "quarter")
    @SerializedName("quarter")
    var quarter : String = ""

)