package cn.jiliapp.woodenfish.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.jiliapp.woodenfish.model.MeritsDTO
import cn.jiliapp.woodenfish.repository.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    val merits:Flow<MeritsDTO> = datastoreRepository.read


    fun saveMerits(meritsDTO: MeritsDTO){
       viewModelScope.launch {
           datastoreRepository.save(meritsDTO)
       }
    }
}