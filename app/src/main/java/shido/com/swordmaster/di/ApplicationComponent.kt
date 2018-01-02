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

    //https://antonioleiva.com/dagger-android-kotlin/
    //https://github.com/satorufujiwara/kotlin-architecture-components/tree/master/app/src/main/java/jp/satorufujiwara/kotlin/di
    //https://medium.com/@burakeregar/kotlin-in-android-mvp-dependency-injection-dagger2-clean-architecture-13a47869dba4
    fun inject(listFragment: ListFragment)
    fun inject(createFragment: CreateFragment)
    fun inject(detailFragment: DetailFragment)

    fun application(): Application

}