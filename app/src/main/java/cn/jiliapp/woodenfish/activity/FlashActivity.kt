package cn.jiliapp.woodenfish.activity

import android.os.Build
import android.os.Bundle
import android.view.*
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.woodenfish.databinding.ActivityFlashBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FlashActivity : ExtendAppCompatActivity() {

    private fun toggleFullScreen() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFlashBinding.inflate(layoutInflater);
        setContentView(binding.root);
        toggleFullScreen();
        binding.root.setOnClickListener {
           startActivity(HomeWorkActivity::class.java)
        }
    }
}