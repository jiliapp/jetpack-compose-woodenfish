package cn.jiliapp.library.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

open class ExtendAppCompatActivity() : AppCompatActivity() {

    //为AppCompatActivity添加扩展函数
    open fun AppCompatActivity.startActivity(activityClass: Class<out AppCompatActivity?>?) {
        super.startActivity(Intent(baseContext,activityClass))
    }

    open fun AppCompatActivity.startActivityResult(activityClass: Class<out AppCompatActivity?>?,activityResultLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent(baseContext,activityClass)
        activityResultLauncher.launch(intent)
    }

    open fun AppCompatActivity.toggleFullScreen() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController
            if(controller != null) {
                if (controller.systemBarsBehavior == WindowInsetsController.BEHAVIOR_DEFAULT) {
                    controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    //导航条
                    controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                } else {
                    controller.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_DEFAULT
                }
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }



}