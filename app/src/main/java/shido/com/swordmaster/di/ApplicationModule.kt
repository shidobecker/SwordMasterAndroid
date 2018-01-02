package shido.com.swordmaster.di

import android.app.Application
import dagger.Module
import dagger.Provides
import shido.com.swordmaster.SwordMasterApplication

/**
 * Created by Shido on 02/01/2018.
 */
@Module
class ApplicationModule(val application: SwordMasterApplication) { //Modules Classes - Responsibles for creating the dependencies

    @Provides
    fun provideSwordMasterApplication(): SwordMasterApplication = application

    @Provides
    fun providesApplication(): Application = application

}