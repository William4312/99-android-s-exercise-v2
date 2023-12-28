package co.ninetynine.android.exercisev2.search.data.repository

import co.ninetynine.android.exercisev2.data.database.AppDatabase
import co.ninetynine.android.exercisev2.data.database.ListingItemEntity
import co.ninetynine.android.exercisev2.di.RetrofitModule
import co.ninetynine.android.exercisev2.search.data.service.SearchService
import co.ninetynine.android.exercisev2.search.model.Address
import co.ninetynine.android.exercisev2.search.model.Attributes
import co.ninetynine.android.exercisev2.search.model.ListingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private var service: SearchService,
    private var database: AppDatabase
) {

    suspend fun getSearchResults(): List<ListingItem> = withContext(Dispatchers.IO) {
        // Retrieve cached results from the database
        val cachedResults = database.getListingDao().getAllListings().value
        if (!cachedResults.isNullOrEmpty()) {
            // Convert ListingItemEntity to ListingItem and return
            return@withContext cachedResults.map { it.toListingItem() }
        } else {
            // Fetch results from the network service
            val resultsFromService = service.getSearchResults()

            // Cache these results in the database
            database.getListingDao().insertAll(resultsFromService.map { it.toEntity() })

            // Return the results from the service
            return@withContext resultsFromService
        }
    }

    private fun ListingItem.toEntity(): ListingItemEntity {
        return ListingItemEntity(
            id = this.id,
            district = this.address.district,
            streetName = this.address.streetName,
            areaSize = this.attributes.areaSize,
            bathrooms = this.attributes.bathrooms,
            bedrooms = this.attributes.bedrooms,
            price = this.attributes.price,
            category = this.category,
            completedAt = this.completedAt,
            photo = this.photoUrl,
            projectName = this.projectName,
            tenure = this.tenure
        )
    }
    private fun ListingItemEntity.toListingItem(): ListingItem {
        return ListingItem(
            address = Address(
                district = this.district,
                streetName = this.streetName
            ),
            attributes = Attributes(
                areaSize = this.areaSize,
                bathrooms = this.bathrooms,
                bedrooms = this.bedrooms,
                price = this.price
            ),
            category = this.category,
            completedAt = this.completedAt,
            id = this.id,
            photoUrl = this.photo,
            projectName = this.projectName,
            tenure = this.tenure
        )
    }

}
