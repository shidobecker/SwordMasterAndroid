package shido.com.swordmaster.create

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import shido.com.swordmaster.R
import shido.com.swordmaster.list.ListFragment
import shido.com.swordmaster.util.BaseActivity

class CreateActivity : BaseActivity() {

    private val CREATE_FRAG = "CREATE_FRAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = supportFragmentManager.findFragmentByTag(CREATE_FRAG) ?: CreateFragment.newInstance()

        addFragmentToActivity(supportFragmentManager, fragment, R.id.root_activity_create , CREATE_FRAG)
    }
}
