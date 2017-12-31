package shido.com.swordmaster.logic

import android.util.Log
import android.view.View
import shido.com.swordmaster.data.DataSourceInterface
import shido.com.swordmaster.data.ListItem
import shido.com.swordmaster.view.ViewInterface

/**
 * Created by Shido on 30/12/2017.
 */
class Controller(val view: ViewInterface, val dataSource: DataSourceInterface) {

    var tempListItem: ListItem? = null
    var tempListItemPosition: Int = 0

    fun getListFromDataSource() {
        val listOfItems = dataSource.getListOfData()
        view.setUpAdapterAndView(listOfItems)
    }

    fun onListItemClick(listItem: ListItem, itemView: View) {
        view.startDetailActivity(listItem.dateAndTime, listItem.message, listItem.colorResource, itemView)
    }

    fun createNewItem() {
       val newItem = dataSource.createNewItem()

        view.addNewListItemToView(newItem)
    }

    fun onListItemSwiped(position: Int, listItem: ListItem) {
        //ensure that the view and the data layers have consistent state
        tempListItemPosition = position
        tempListItem = listItem
        view.deleteListItemAt(tempListItemPosition)

        view.showUndoSnackbar()

    }

    fun onUndoConfirmed() {
        view.insertItemBackAt(checkNotNull(tempListItem), tempListItemPosition)
    }

    fun onSnackbarTimeout() {
        dataSource.deleteListItem(checkNotNull(tempListItem))
    }


}