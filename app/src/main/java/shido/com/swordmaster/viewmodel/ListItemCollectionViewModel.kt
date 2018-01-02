package shido.com.swordmaster.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import shido.com.swordmaster.data.ListItem
import shido.com.swordmaster.data.ListItemRepository

/**
 * Created by Shido on 02/01/2018.
 */
class ListItemCollectionViewModel(val repository: ListItemRepository) : ViewModel() {

    fun getListItems(): LiveData<List<ListItem>> = repository.getListItems()

    fun deleteItem(listItem: ListItem) = launch(CommonPool){
        repository.deleteListItem(listItem)
    }

}