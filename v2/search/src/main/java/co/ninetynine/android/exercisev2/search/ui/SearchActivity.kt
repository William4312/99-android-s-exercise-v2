package co.ninetynine.android.exercisev2.search.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.ninetynine.android.exercisev2.NinetyNineExerciseApp
import co.ninetynine.android.exercisev2.di.SearchModuleDependencies
import co.ninetynine.android.exercisev2.search.databinding.ActivitySearchBinding
import co.ninetynine.android.exercisev2.search.di.DaggerSearchComponent
import co.ninetynine.android.exercisev2.search.model.ListingItem
import co.ninetynine.android.exercisev2.search.viewmodel.SearchViewModel
import co.ninetynine.android.exercisev2.search.viewmodel.SearchViewModelFactory
import co.ninetynine.android.exercisev2.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setupSearchList()
        observeLiveData()
    }

    private fun injectDependencies() {
        DaggerSearchComponent.factory().create(
            EntryPointAccessors.fromApplication((application as NinetyNineExerciseApp), SearchModuleDependencies::class.java)
        ).inject(this)
    }

    override fun createThisViewBinding() = ActivitySearchBinding.inflate(layoutInflater)

    private fun setupSearchList() {
        getThisViewBinding().searchResultsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SearchAdapter(context)
        }
    }

    private fun observeLiveData() {
        viewModel.listingItems.observeNotNull {
            onSearchItemsChanged(it)
        }
    }

    private fun onSearchItemsChanged(items: List<ListingItem>) {
        getSearchListAdapter().setSearchItems(items)
    }

    private fun getSearchListAdapter() = getThisViewBinding()
        .searchResultsList.adapter as SearchAdapter

}
