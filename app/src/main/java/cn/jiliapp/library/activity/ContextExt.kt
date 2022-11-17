package cn.jiliapp.library.activity

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class ContextExt {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "wooden_fish")

}