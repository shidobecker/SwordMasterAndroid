package shido.com.swordmaster.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import shido.com.swordmaster.R


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var itemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            itemId = arguments.getString(itemIdArg)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val textView = TextView(activity)
        textView.setText(R.string.hello_blank_fragment)
        return textView
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val itemIdArg = "ITEM_ID"

        fun newInstance(itemId: String): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString(itemIdArg,itemId)
            fragment.arguments = args
            return fragment
        }
    }

}
