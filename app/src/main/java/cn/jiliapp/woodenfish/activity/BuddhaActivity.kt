package cn.jiliapp.woodenfish.activity

import android.os.Bundle
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.woodenfish.databinding.ActivityBuddhaBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 佛祖
 */

@AndroidEntryPoint
class BuddhaActivity : ExtendAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBuddhaBinding.inflate(layoutInflater);
        setContentView(binding.root);
    }
}