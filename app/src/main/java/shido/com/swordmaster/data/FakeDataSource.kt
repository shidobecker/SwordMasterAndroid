package shido.com.swordmaster.data

import shido.com.swordmaster.R
import java.util.*

/**
 * Test Double
 *
 * Created by Shido on 30/12/2017.
 */
class FakeDataSource : DataSourceInterface {


    private val sizeOfCollection = 12
    private val random: Random = Random()

    private val datesAndTimes = arrayOf("6:30AM 06/01/2017",
            "9:26PM 04/22/2013",
            "2:01PM 12/02/2015",
            "2:43AM 09/7/2018")

    private val messages = arrayOf("Check out content like Fragmented Podcast to expose yourself to the knowledge, ideas, " +
            "and opinions of experts in your field", "Look at Open Source Projects like Android Architecture Blueprints to see how experts" +
            " design and build Apps", "Write lots of Code and Example Apps. Writing good Quality Code in an efficient manner " +
            "is a Skill to be practiced like any other.", "If at first something doesn't make any sense, find another explanation. We all " +
            "learn/teach different from each other. Find an explanation that speaks to you.")

    //private val drawables = intArrayOf(R.drawable.green_drawable, R.drawable.red_drawable, R.drawable.blue_drawable, R.drawable.yellow_drawable)

    private val colours = arrayOf(R.color.RED,
            R.color.BLUE,
            R.color.GREEN,
            R.color.YELLOW)


    override fun getListOfData(): List<ListItem> {
        val listOfData = mutableListOf<ListItem>()
        val random = Random()
        //make 12 semi-random items
        for (i in 0..11) {

            val randomOne = random.nextInt(4)
            val randomTwo = random.nextInt(4)
            val randomThree = random.nextInt(4)

            val listItem = ListItem(dateAndTime = datesAndTimes[randomOne],
                    message = messages[randomTwo], colorResource = colours[randomThree])

            listOfData.add(listItem)
        }

        return listOfData
    }

    override fun createNewItem(): ListItem {
        val randomOne = random.nextInt(4)
        val randomTwo = random.nextInt(4)
        val randomThree = random.nextInt(4)

        return ListItem(dateAndTime = datesAndTimes[randomOne],
                message = messages[randomTwo], colorResource = colours[randomThree])
    }

    override fun deleteListItem(listItem: ListItem) {
    }
}