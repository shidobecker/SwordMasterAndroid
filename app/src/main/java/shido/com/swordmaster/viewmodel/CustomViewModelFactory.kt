package shido.com.swordmaster.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import shido.com.swordmaster.data.ListItemRepository

/**
 * Created by Shido on 02/01/2018.
 */
class CustomViewModelFactory(val repository: ListItemRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListItemCollectionViewModel::class.java)){
            return ListItemCollectionViewModel(repository) as T
        }else{
            throw IllegalArgumentException("View Model not Found")
        }

    }
//https://gist.github.com/Elforama/969c2de0b3227f927fbf3f65654acf63
    //https://github.com/googlesamples/android-architecture-components/blob/d4891316b14d6a0c870bca558a60b1d535ce1df6/GithubBrowserSample/app/src/main/java/com/android/example/github/viewmodel/GithubViewModelFactory.java
    //https://qanda.tech/android/799637/kotlin-dagger-inject-map-for-viewmodel-factory
}