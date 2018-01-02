package shido.com.swordmaster.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Shido on 02/01/2018.
 */
@Database(entities = [(ListItem::class)], version = 1)
abstract class ListItemDatabase : RoomDatabase() {

    abstract fun listItemDao(): ListItemDao

}