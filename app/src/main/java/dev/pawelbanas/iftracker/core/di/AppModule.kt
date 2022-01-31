package dev.pawelbanas.iftracker.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BinderModule {

        @Binds
        @Singleton
        abstract fun bindDispatcher(dispatcher: DispatchersImpl): Dispatchers
    }
}