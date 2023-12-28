package co.ninetynine.android.exercisev2.search.di

import co.ninetynine.android.exercisev2.di.SearchModuleDependencies
import co.ninetynine.android.exercisev2.search.ui.SearchActivity
import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [SearchModuleDependencies::class], modules = [SearchModule::class])
@Singleton
interface SearchComponent {
    fun inject(activity: SearchActivity)

    @Component.Factory
    interface Factory {
        fun create(dependencies: SearchModuleDependencies): SearchComponent
    }
}
