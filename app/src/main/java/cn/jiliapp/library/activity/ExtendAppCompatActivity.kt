package cn.jiliapp.library.activity

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
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


}