package co.ninetynine.android.exercisev2.search.data.service

import co.ninetynine.android.exercisev2.search.model.ListingItem
import retrofit2.http.GET

interface SearchService {
    @GET("/listings.json")
    suspend fun getSearchResults(): List<ListingItem>

}
