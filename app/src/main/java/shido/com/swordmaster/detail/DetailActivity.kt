package shido.com.swordmaster.detail

import android.os.Bundle
import android.widget.Toast
import shido.com.swordmaster.R
import shido.com.swordmaster.util.BaseActivity

class DetailActivity : BaseActivity() {

    private val EXTRA_ITEM_ID = "EXTRA_ITEM_ID"
    private val DETAIL_FRAG = "DETAIL_FRAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra(EXTRA_ITEM_ID)){

            val itemId = intent.getStringExtra(EXTRA_ITEM_ID)

            val fragment = supportFragmentManager.findFragmentByTag(DETAIL_FRAG) ?: DetailFragment.newInstance(itemId)
            addFragmentToActivity(supportFragmentManager, fragment, R.id.root_activity_detail , DETAIL_FRAG)
        }else{
            Toast.makeText(this, R.string.error_no_extra_found, Toast.LENGTH_LONG).show();

        }


    }
}
