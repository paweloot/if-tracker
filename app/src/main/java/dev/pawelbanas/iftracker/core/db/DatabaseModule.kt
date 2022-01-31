package dev.pawelbanas.iftracker.core.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.pawelbanas.iftracker.core.db.dao.MealDataDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): IfTrackerDatabase =
        Room.databaseBuilder(applicationContext, IfTrackerDatabase::class.java, "iftracker-database").build()

    @Provides
    @Singleton
    fun provideMealDataDao(db: IfTrackerDatabase): MealDataDao = db.mealDataDao()
}