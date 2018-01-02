package shido.com.swordmaster.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Shido on 30/12/2017.
 */
@Entity
class ListItem(
        @PrimaryKey
        val id: String = UUID.randomUUID().toString(),
        val dateAndTime: String,
        val message: String,
        val colorResource: Int)