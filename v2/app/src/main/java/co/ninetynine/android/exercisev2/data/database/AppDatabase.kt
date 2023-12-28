package co.ninetynine.android.exercisev2.data.database

import ListingItemDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListingItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun getListingDao(): ListingItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Return the existing instance if it exists
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            // Otherwise, create a new instance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nn-exercise-database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}