package cn.jiliapp.woodenfish.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


open class BaseViewModel(application: Application) : AndroidViewModel(application){

    private var isLoading = MutableLiveData<Boolean>()


    fun getLoading(): LiveData<Boolean> {
        return isLoading
    }


    fun isLoading(): Boolean {
        return isLoading.value?:false;
    }

    fun setLoading(loading : Boolean){
         isLoading.value=loading;
    }



}