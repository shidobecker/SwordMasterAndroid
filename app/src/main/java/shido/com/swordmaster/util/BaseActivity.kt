package shido.com.swordmaster.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

/**
 * Created by Shido on 02/01/2018.
 */
abstract class BaseActivity: AppCompatActivity() {

    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment,
                              frameId: Int,
                              tag: String){
        fragmentManager.beginTransaction().replace(frameId, fragment, tag).commit()
    }

}