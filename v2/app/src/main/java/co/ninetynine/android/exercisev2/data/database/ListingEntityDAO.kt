import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.ninetynine.android.exercisev2.data.database.ListingItemEntity

@Dao
interface ListingItemDao {
    @Query("SELECT * FROM listing_items")
    fun getAllListings(): LiveData<List<ListingItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listingItems: List<ListingItemEntity>)
}
