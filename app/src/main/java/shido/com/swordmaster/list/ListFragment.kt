package shido.com.swordmaster.list


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.transition.Fade
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_data.view.*

import shido.com.swordmaster.R
import shido.com.swordmaster.SwordMasterApplication
import shido.com.swordmaster.data.ListItem
import shido.com.swordmaster.viewmodel.ListItemCollectionViewModel
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var adapter: CustomAdapter

    val viewModel: ListItemCollectionViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
                .get(ListItemCollectionViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity.application as SwordMasterApplication)
                .applicationComponent.inject(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getListItems().observe(this, Observer {
            adapter.listOfItems = checkNotNull(it?.toMutableList())
            adapter.notifyDataSetChanged()
        })

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val textView = TextView(activity)
        textView.setText(R.string.hello_blank_fragment)
        return textView
    }

    companion object {
        val EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME"
        val EXTRA_MESSAGE = "EXTRA_MESSAGE"
        val EXTRA_COLOR = "EXTRA_COLOR"

        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }


    fun onClickFab() {
        //controller.createNewItem()
    }

    fun showUndoSnackbar() {
        Snackbar.make(root_list_activity, "Item Deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo", {
                   // controller.onUndoConfirmed()
                }).addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            //When the snackbar disapears goes to this callback
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
               // controller.onSnackbarTimeout()
            }
        }).show()

    }


    fun addNewListItemToView(newItem: ListItem) {
        adapter.listOfItems.add(newItem)
        adapter.notifyItemInserted(adapter.listOfItems.lastIndex)
        recyclerList.smoothScrollToPosition(adapter.listOfItems.lastIndex)
    }

    fun insertItemBackAt(listItem: ListItem, tempListItemPosition: Int) {
        adapter.listOfItems.add(tempListItemPosition, listItem)
        adapter.notifyItemInserted(tempListItemPosition)
        recyclerList.smoothScrollToPosition(tempListItemPosition)
    }


    fun startDetailActivity(dateAndTime: String, message: String, colorResource: Int, view: View) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(EXTRA_DATE_AND_TIME, dateAndTime)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_COLOR, colorResource)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /*     activity.window.enterTransition = Fade(Fade.IN)
                 activity.window.enterTransition = Fade(Fade.OUT)
                 val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,
                         Pair<View, String>(view.lbl_date_and_time, getString(R.string.transition_date)),
                         Pair<View, String>(view.lbl_message, getString(R.string.transition_message)))*/
            // startActivity(intent, activityOptions.toBundle())
            startActivity(intent)

        } else {
            startActivity(intent)
        }
    }

    fun setUpAdapterAndView(listItem: List<ListItem>) {
        val layoutManager = LinearLayoutManager(context)
        recyclerList.layoutManager = layoutManager
        adapter = CustomAdapter(context, { item, itemView ->
            //controller.onListItemClick(item, itemView)
        })
        adapter.listOfItems = listItem.toMutableList()
        adapter.notifyDataSetChanged()
        recyclerList.adapter = adapter

        val itemDecoration = DividerItemDecoration(recyclerList.context, layoutManager.orientation)
        itemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_white))
        recyclerList.addItemDecoration(itemDecoration)

        val itemTouchHelper = ItemTouchHelper(createHelperCallback())
        itemTouchHelper.attachToRecyclerView(recyclerList)

    }

    fun deleteListItemAt(position: Int) {
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
                //controller.onListItemSwiped(position, adapter.listOfItems[position])
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