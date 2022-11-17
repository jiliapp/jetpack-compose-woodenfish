package cn.jiliapp.woodenfish.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import cn.jiliapp.woodenfish.model.MeritsDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

 class DatastoreExtend(val context:Context) {

    private val  Context.dataStore : DataStore<Preferences> by preferencesDataStore("wooden_fish")

    private val nameKey= stringPreferencesKey("username");
    private val meritsKey= intPreferencesKey("merits");


    suspend fun save(merits: MeritsDTO){
        context.dataStore.edit {
            it[meritsKey]= merits.knock;
        }
    }

     suspend fun update(merits: MeritsDTO){
       /*  context.dataStore.updateData {
         }*/
     }
    val read:Flow<MeritsDTO> =  context.dataStore.data.map { it ->
         MeritsDTO(it[meritsKey]?:0,1L)
    }




}