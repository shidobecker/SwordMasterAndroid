package shido.com.swordmaster

import android.app.Application
import shido.com.swordmaster.di.ApplicationComponent
import shido.com.swordmaster.di.ApplicationModule
import shido.com.swordmaster.di.DaggerApplicationComponent
import shido.com.swordmaster.di.RoomModule

/**
 * Created by Shido on 02/01/2018.
 */
open class SwordMasterApplication: Application() {

  open val applicationComponent: ApplicationComponent by lazy {
       DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .roomModule(RoomModule(this))
                .build()
    }


    override fun onCreate() {
        super.onCreate()
        //2 Differents components classes
        //Each component list the dependencies required for a particular objects

    }

}