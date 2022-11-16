package cn.jiliapp.woodenfish.activity

import android.os.Bundle
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.woodenfish.databinding.ActivityTempleBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 寺庙
 */
@AndroidEntryPoint
class TempleActivity : ExtendAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTempleBinding.inflate(layoutInflater);
        setContentView(binding.root);
    }
}