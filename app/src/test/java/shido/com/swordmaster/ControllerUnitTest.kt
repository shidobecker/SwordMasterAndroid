package shido.com.swordmaster

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import shido.com.swordmaster.data.ListItemDao
import shido.com.swordmaster.data.ListItem
import shido.com.swordmaster.logic.Controller
import shido.com.swordmaster.list.ViewInterface

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ControllerUnitTest {

    /**
     * Test Double:
     * Specifically a 'Mock'
     */
    val dataSource = mock<ListItemDao>()

    val view = mock<ViewInterface>()

    lateinit var controller : Controller

    val listItem = ListItem(dateAndTime = "6:30AM 06/01/2017",
            message = "Check out content like Fragmented Podcast to expose yourself to the knowledge, ideas, ",
            colorResource =  123)

    @Before
    fun setupTest(){
        controller = Controller(view, dataSource)
    }

    @Test
    fun onGetListDataSuccessful(){
        //Setup any data we need for test
        val listItens = mutableListOf<ListItem>()
        listItens.add(listItem)

        //Setup our mocks to return the data we want
        Mockito.`when`(dataSource.getListItems()).thenReturn(listItens) //Then something calls dataSource.getListItems, return listItens declared above

        //Call the method we are testing
        controller.getListFromDataSource()

        //Check how the Tested Class responds to the data it receives or test it's behavior
        Mockito.verify(view).setUpAdapterAndView(listItens)//Check if this method is called with a list of data

    }

    @Test
    fun onListItemClicked(){
        controller.onListItemClick(listItem)

        Mockito.verify(view).startDetailActivity(listItem.dateAndTime, listItem.message, listItem.colorResource)
    }

}
