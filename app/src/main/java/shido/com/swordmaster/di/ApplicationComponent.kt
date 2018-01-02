package shido.com.swordmaster.di

import android.app.Application
import dagger.Component
import shido.com.swordmaster.create.CreateFragment
import shido.com.swordmaster.detail.DetailFragment
import shido.com.swordmaster.list.ListFragment
import javax.inject.Singleton

/**
 * Created by Shido on 02/01/2018.
 */
@Singleton //Scope Singleton cause there's only one application
@Component(modules = [ApplicationModule::class, RoomModule::class]) //Modules Required to satify all the dependencies
interface ApplicationComponent {

    fun inject(listFragment: ListFragment)
    fun inject(createFragment: CreateFragment)
    fun inject(detailFragment: DetailFragment)

    fun application(): Application

}