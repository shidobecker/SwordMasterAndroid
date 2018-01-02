package shido.com.swordmaster.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * This is a contract between classes that dictate how they can talk to each other without giving implementation details
 * Created by Shido on 30/12/2017.
 */
@Dao
interface ListItemDao {

    @Query("Select * from ListItem")
    fun getListItems(): LiveData<List<ListItem>>

    @Query("Select * from ListItem where id = :id")
    fun getListItemById(id: String): LiveData<ListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createNewItem(listItem: ListItem)

    @Delete
    fun deleteListItem(listItem: ListItem)

}