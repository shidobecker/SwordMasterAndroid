package shido.com.swordmaster.view

import shido.com.swordmaster.data.ListItem

/**
 * Created by Shido on 30/12/2017.
 */
interface ViewInterface {

    fun startDetailActivity(dateAndTime: String, message: String, colorResource: Int)

    fun setUpAdapterAndView(listItem: List<ListItem>)

}