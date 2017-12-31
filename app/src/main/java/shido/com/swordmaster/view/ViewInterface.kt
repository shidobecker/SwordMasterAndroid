package shido.com.swordmaster.view

import android.view.View
import shido.com.swordmaster.data.ListItem

/**
 * Created by Shido on 30/12/2017.
 */
interface ViewInterface {

    fun startDetailActivity(dateAndTime: String, message: String, colorResource: Int, view: View)

    fun setUpAdapterAndView(listItem: List<ListItem>)
    fun addNewListItemToView(newItem: ListItem)
    fun deleteListItemAt(position: Int)
    fun showUndoSnackbar()
    fun insertItemBackAt(listItem: ListItem, tempListItemPosition: Int)

}