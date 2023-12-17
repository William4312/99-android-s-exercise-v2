package co.ninetynine.android.exercisev2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ListingItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase()