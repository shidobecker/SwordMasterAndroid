package shido.com.swordmaster.list

import android.os.Bundle
import android.os.PersistableBundle
import shido.com.swordmaster.R
import shido.com.swordmaster.util.BaseActivity

class ListActivity : BaseActivity() {

    private val LIST_FRAG = "LIST_FRAG"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val fragment = supportFragmentManager.findFragmentByTag(LIST_FRAG) ?: ListFragment.newInstance()

        addFragmentToActivity(supportFragmentManager, fragment, R.id.root_activity_list , LIST_FRAG)
    }

}
