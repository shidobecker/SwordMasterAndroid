package shido.com.swordmaster.data

import android.arch.lifecycle.LiveData
import javax.inject.Inject

/**
 * Created by Shido on 02/01/2018.
 */
class ListItemRepository @Inject constructor(private val listDao: ListItemDao) {

    fun getListItems(): LiveData<List<ListItem>> = listDao.getListItems()

    fun getListItem(id: String): LiveData<ListItem> = listDao.getListItemById(id)

    fun deleteListItem(listItem: ListItem) = listDao.deleteListItem(listItem)

    fun createListItem(listItem: ListItem) = listDao.createNewItem(listItem)

}