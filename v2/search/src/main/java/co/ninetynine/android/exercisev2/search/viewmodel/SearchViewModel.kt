package co.ninetynine.android.exercisev2.search.viewmodel

import androidx.lifecycle.*
import co.ninetynine.android.exercisev2.search.data.repository.SearchRepository
import co.ninetynine.android.exercisev2.search.model.ListingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
) : ViewModel() {
    private val _listingItems = MutableLiveData<List<ListingItem>>()
    val listingItems: LiveData<List<ListingItem>> = _listingItems

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchSearchResults()
    }

    private fun fetchSearchResults() {
        viewModelScope.launch {
            try {
                val results = repository.getSearchResults()
                _listingItems.postValue(results)
            } catch (e: Exception) {
                _error.postValue("Failed to fetch data: ${e.message}")
            }
        }
    }
}

class SearchViewModelFactory(
    private val repository: SearchRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Cannot create `SearchViewModel` from class: ${modelClass.name}")
    }
}
