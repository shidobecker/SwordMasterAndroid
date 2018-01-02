package shido.com.swordmaster.list

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_detail.*
import shido.com.swordmaster.R


class DetailActivity : AppCompatActivity() {

    private val EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME"
    private val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    private val EXTRA_COLOR = "EXTRA_COLOR"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val i = intent
        val dateAndTimeExtra = i.getStringExtra(EXTRA_DATE_AND_TIME)
        val messageExtra = i.getStringExtra(EXTRA_MESSAGE)
        val drawableResourceExtra = i.getIntExtra(EXTRA_COLOR, 0)

        lbl_date_and_time_header.text = dateAndTimeExtra
        lbl_message_body.text = messageExtra

        imv_colored_background.setBackgroundColor(ContextCompat.getColor(this, drawableResourceExtra))

    }
}
