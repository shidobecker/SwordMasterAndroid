package shido.com.swordmaster.view

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.transition.Fade
import android.util.Pair
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

    lateinit var adapter: CustomAdapter

    lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //This is dependency injection, this class is satisfying the dependencies for Controller
        controller = Controller(this, FakeDataSource())
        controller.getListFromDataSource()

        fab_create_new_item.setOnClickListener {
            onClickFab()
        }

    }


    fun onClickFab() {
        controller.createNewItem()
    }

    override fun showUndoSnackbar() {
        Snackbar.make(root_list_activity, "Item Deleted", Snackbar.LENGTH_LONG )
                .setAction("Undo", {
                    controller.onUndoConfirmed()
                }).addCallback(object: BaseTransientBottomBar.BaseCallback<Snackbar>(){//When the snackbar disapears goes to this callback
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                controller.onSnackbarTimeout()
            }
        }).show()

    }



    override fun addNewListItemToView(newItem: ListItem) {
        adapter.listOfItems.add(newItem)
        adapter.notifyItemInserted(adapter.listOfItems.lastIndex)
        recyclerList.smoothScrollToPosition(adapter.listOfItems.lastIndex)
    }

    override fun insertItemBackAt(listItem: ListItem, tempListItemPosition: Int) {
        adapter.listOfItems.add(tempListItemPosition, listItem)
        adapter.notifyItemInserted(tempListItemPosition)
        recyclerList.smoothScrollToPosition(tempListItemPosition)
    }


    override fun startDetailActivity(dateAndTime: String, message: String, colorResource: Int, view: View) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_DATE_AND_TIME, dateAndTime)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_COLOR, colorResource)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.enterTransition = Fade(Fade.IN)
            window.enterTransition = Fade(Fade.OUT)
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair<View, String>(view.lbl_date_and_time, getString(R.string.transition_date)),
                    Pair<View, String>(view.lbl_message, getString(R.string.transition_message)))
            startActivity(intent, activityOptions.toBundle())

        }else {
            startActivity(intent)
        }
    }

    override fun setUpAdapterAndView(listItem: List<ListItem>) {
        val layoutManager = LinearLayoutManager(this)
        recyclerList.layoutManager = layoutManager
        adapter = CustomAdapter(this, { item, itemView ->
            controller.onListItemClick(item, itemView)
        })
        adapter.listOfItems = listItem.toMutableList()
        adapter.notifyDataSetChanged()
        recyclerList.adapter = adapter

        val itemDecoration = DividerItemDecoration(recyclerList.context, layoutManager.orientation)
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_white))
        recyclerList.addItemDecoration(itemDecoration)

        val itemTouchHelper = ItemTouchHelper(createHelperCallback())
        itemTouchHelper.attachToRecyclerView(recyclerList)

    }

    override fun deleteListItemAt(position: Int) {
        adapter.listOfItems.removeAt(position)
        adapter.notifyItemRemoved(position)
    }


    private fun createHelperCallback(): ItemTouchHelper.Callback {
        /*First Param is for Up/Down motion, second is for Left/Right.
             Note that we can supply 0, one constant (e.g. ItemTouchHelper.LEFT), or two constants (e.g.
             ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) to specify what directions are allowed.
             */
        return object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            //not used, as the first parameter above is 0
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                controller.onListItemSwiped(position, adapter.listOfItems[position])
            }
        }
    }


    class CustomAdapter(val context: Context, val onClick: (item: ListItem, itemView: View) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        var listOfItems = mutableListOf<ListItem>()

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
                itemView.root_list_item.setOnClickListener { onClick(listItem, itemView) }
            }
        }
    }


}
