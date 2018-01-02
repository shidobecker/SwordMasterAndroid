package shido.com.swordmaster.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import shido.com.swordmaster.data.ListItemDao
import shido.com.swordmaster.data.ListItemDatabase
import shido.com.swordmaster.data.ListItemRepository
import shido.com.swordmaster.viewmodel.CustomViewModelFactory
import javax.inject.Singleton


/**
 * Responsable for creating / satisfying dependencies for room
 * Created by Shido on 02/01/2018.
 */
@Module
class RoomModule(val application: Application) {

    private var database: ListItemDatabase = Room.databaseBuilder(
            application,
            ListItemDatabase::class.java,
            "ListItem.db"
    ).build()


    @Provides //If a ListItemRepository is needed (Like in viewModels) this is where you can get it
    @Singleton
    fun provideListItemRepository(listItemDao: ListItemDao): ListItemRepository {
        return ListItemRepository(listItemDao)
    }

    @Provides
    @Singleton
    fun provideListItemDao(database: ListItemDatabase): ListItemDao {
        return database.listItemDao()
    }

    @Provides
    @Singleton
    fun provideListItemDatabase(application: Application): ListItemDatabase {
        return database
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: ListItemRepository): ViewModelProvider.Factory {
        return CustomViewModelFactory(repository)
    }


}