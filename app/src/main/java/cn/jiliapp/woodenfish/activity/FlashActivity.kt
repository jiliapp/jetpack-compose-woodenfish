package cn.jiliapp.woodenfish.activity

import android.os.Build
import android.os.Bundle
import android.view.*
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.woodenfish.databinding.ActivityFlashBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FlashActivity : ExtendAppCompatActivity() {


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