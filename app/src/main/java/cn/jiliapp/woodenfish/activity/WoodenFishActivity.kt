package cn.jiliapp.woodenfish.activity

import android.os.Bundle
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.woodenfish.databinding.ActivityWoodenFishBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 木鱼
 */
@AndroidEntryPoint
class WoodenFishActivity : ExtendAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWoodenFishBinding.inflate(layoutInflater);
        setContentView(binding.root);
    }
}