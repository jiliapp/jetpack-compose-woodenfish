package cn.jiliapp.woodenfish.repository


import cn.jiliapp.woodenfish.model.MeritsDTO
import kotlinx.coroutines.flow.Flow

class DatastoreRepository(private val datastoreExtend :DatastoreExtend) {

    suspend fun save(meritsDTO: MeritsDTO){
        datastoreExtend.save(meritsDTO)
    }

    val read: Flow<MeritsDTO> = datastoreExtend.read


}