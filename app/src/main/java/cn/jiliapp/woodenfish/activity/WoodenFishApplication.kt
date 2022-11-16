package cn.jiliapp.woodenfish.activity

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp
import org.conscrypt.Conscrypt
import java.security.Security

@HiltAndroidApp
class WoodenFishApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //okhttp https
        //https://square.github.io/okhttp/
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}