package shido.com.swordmaster.logic

import android.util.Log
import shido.com.swordmaster.data.DataSourceInterface
import shido.com.swordmaster.data.ListItem
import shido.com.swordmaster.view.ViewInterface

/**
 * Created by Shido on 30/12/2017.
 */
class Controller(val view: ViewInterface, val dataSource: DataSourceInterface) {


    fun getListFromDataSource() {
        val listOfItems = dataSource.getListOfData()
        view.setUpAdapterAndView(listOfItems)
    }

    fun onListItemClick(listItem: ListItem) {
        view.startDetailActivity(listItem.dateAndTime, listItem.message, listItem.colorResource)
    }


}