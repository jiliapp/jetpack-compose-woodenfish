package cn.jiliapp.woodenfish.viewmodel

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import cn.jiliapp.woodenfish.model.AuthVO
import cn.jiliapp.woodenfish.model.KcLoginDTO
import cn.jiliapp.woodenfish.model.MeritsDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeWorkViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel(application) {




    private var _knockMeritsList: MutableLiveData<List<MeritsDTO>> = MutableLiveData(listOf())
    val  knockMeritsList: LiveData<List<MeritsDTO>> =_knockMeritsList

    fun  knockMerits(meritsDTO: MeritsDTO)  {
        _knockMeritsList.value = _knockMeritsList.value?.plus(meritsDTO) ?: listOf(meritsDTO)
    }





}
