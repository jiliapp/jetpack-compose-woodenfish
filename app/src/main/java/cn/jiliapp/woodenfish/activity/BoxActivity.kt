package cn.jiliapp.woodenfish.activity

import android.os.Bundle
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.woodenfish.databinding.ActivityBoxBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

/**
 * 功德箱
 */
@AndroidEntryPoint
class BoxActivity : ExtendAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBoxBinding.inflate(layoutInflater);
        setContentView(binding.root);
    }
}