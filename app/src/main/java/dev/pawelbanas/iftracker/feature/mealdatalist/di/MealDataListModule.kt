package dev.pawelbanas.iftracker.feature.mealdatalist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.pawelbanas.iftracker.feature.mealdatalist.DatabaseMealDataRepository
import dev.pawelbanas.iftracker.feature.mealdatalist.MealDataRepository

@Module
@InstallIn(ActivityComponent::class)
abstract class MealDataListModule {

    @Binds
    abstract fun bindMealDataRepository(repository: DatabaseMealDataRepository): MealDataRepository
}