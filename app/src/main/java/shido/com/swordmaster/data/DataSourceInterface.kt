package shido.com.swordmaster.data

/**
 * This is a contract between classes that dictate how they can talk to each other without giving implementation details
 * Created by Shido on 30/12/2017.
 */
interface DataSourceInterface {

    fun getListOfData(): List<ListItem>

}