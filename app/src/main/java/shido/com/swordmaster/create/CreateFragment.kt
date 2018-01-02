package shido.com.swordmaster.create


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import shido.com.swordmaster.R


/**
 * A simple [Fragment] subclass.
 * Use the [CreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val textView = TextView(activity)
        textView.setText(R.string.hello_blank_fragment)
        return textView
    }

    companion object {


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): CreateFragment {
            val fragment = CreateFragment()
            return fragment
        }
    }

}// Required empty public constructor
