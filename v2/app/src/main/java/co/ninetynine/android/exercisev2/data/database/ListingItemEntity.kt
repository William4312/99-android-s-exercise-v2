package co.ninetynine.android.exercisev2.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

@Entity(tableName = "listing_items")
data class ListingItemEntity(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "completed_at")
    val completedAt: String,

    @ColumnInfo(name = "project_name")
    val projectName: String,

    @ColumnInfo(name = "tenure")
    val tenure: Int,

    @ColumnInfo(name = "photo")
    val photo: String,

    @ColumnInfo(name = "district")
    val district: String,

    @ColumnInfo(name = "street_name")
    val streetName: String,

    @ColumnInfo(name = "area_size")
    val areaSize: Int,

    @ColumnInfo(name = "bathrooms")
    val bathrooms: Int,

    @ColumnInfo(name = "bedrooms")
    val bedrooms: Int,

    @ColumnInfo(name = "price")
    val price: Int
)
