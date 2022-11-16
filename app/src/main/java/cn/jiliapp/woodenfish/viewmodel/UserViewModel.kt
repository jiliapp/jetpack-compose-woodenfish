package cn.jiliapp.woodenfish.viewmodel

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.*
import cn.jiliapp.library.http.HttpStatus
import cn.jiliapp.woodenfish.model.AuthVO
import cn.jiliapp.woodenfish.model.KcAuthVO
import cn.jiliapp.woodenfish.model.KcLoginDTO
import cn.jiliapp.woodenfish.model.LoginDTO
import cn.jiliapp.woodenfish.repository.UserRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel(application) {

    @Inject
    lateinit var gson:Gson;
    @Inject
    lateinit var userRepository: UserRepository;


    private val authVO = MutableLiveData<AuthVO?>()
    private  val kcAuthVO = MutableLiveData<KcAuthVO?>()

   fun login(login: LoginDTO)  {
       setLoading(true)
       viewModelScope.launch {
           val result = userRepository.login(login)
           if (result.code!=HttpStatus.HTTP_OK.code){
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   Toast.makeText(getApplication(), result.msg, Toast.LENGTH_SHORT).show()
               }
           }
           authVO.value = result.data
       }
   }


    fun kcLogin(login: KcLoginDTO)  {
        viewModelScope.launch {
           val result = userRepository.kcLogin(login)
            kcAuthVO.value = result
        }
    }


    fun getAuthVO(): LiveData<AuthVO?> {
        return authVO
    }

    fun getKcAuthVO(): LiveData<KcAuthVO?> {
        return kcAuthVO
    }


}