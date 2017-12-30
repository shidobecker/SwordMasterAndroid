package shido.com.swordmaster.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_data.view.*
import shido.com.swordmaster.R
import shido.com.swordmaster.data.FakeDataSource
import shido.com.swordmaster.data.ListItem
import shido.com.swordmaster.logic.Controller

class ListActivity : AppCompatActivity(), ViewInterface {

    companion object {
        val EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME"
        val EXTRA_MESSAGE = "EXTRA_MESSAGE"
        val EXTRA_COLOR = "EXTRA_COLOR"
    }

    var listOfData = listOf<ListItem>()

    lateinit var adapter: CustomAdapter

    lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //This is dependency injection, this class is satisfying the dependencies for Controller
        controller = Controller(this, FakeDataSource())
        controller.getListFromDataSource()

    }

    override fun startDetailActivity(dateAndTime: String, message: String, colorResource: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_DATE_AND_TIME, dateAndTime)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_COLOR, colorResource)
        startActivity(intent)
    }

    override fun setUpAdapterAndView(listItem: List<ListItem>) {
        recyclerList.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(this, {
            controller.onListItemClick(it)
        })
        adapter.listOfItems = listItem
        adapter.notifyDataSetChanged()
        recyclerList.adapter = adapter

    }



    class CustomAdapter(val context: Context, val onClick: (item: ListItem) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        var listOfItems = listOf<ListItem>()

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_data, parent, false)
            return ViewHolder(view)
        }


        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.bindRow(listOfItems[position])
        }

        override fun getItemCount(): Int = listOfItems.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindRow(listItem: ListItem) {
                itemView.lbl_message.text = listItem.message
                itemView.lbl_date_and_time.text = listItem.dateAndTime
                itemView.imv_list_item_circle.setBackgroundColor(ContextCompat.getColor(context, listItem.colorResource))
                itemView.root_list_item.setOnClickListener { onClick(listItem) }
            }
        }
    }


}
