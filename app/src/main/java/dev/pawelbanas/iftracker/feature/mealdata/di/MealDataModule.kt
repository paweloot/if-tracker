package dev.pawelbanas.iftracker.feature.mealdata.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.pawelbanas.iftracker.feature.mealdata.data.DatabaseMealDataRepository
import dev.pawelbanas.iftracker.feature.mealdata.domain.MealDataRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class MealDataModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMealDataRepository(repository: DatabaseMealDataRepository): MealDataRepository
}