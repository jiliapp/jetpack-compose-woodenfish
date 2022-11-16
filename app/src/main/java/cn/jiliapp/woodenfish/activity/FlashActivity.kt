package cn.jiliapp.woodenfish.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.woodenfish.R
import cn.jiliapp.woodenfish.databinding.ActivityFlashBinding
import cn.jiliapp.woodenfish.databinding.ActivityHomeWorkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlashActivity : ExtendAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFlashBinding.inflate(layoutInflater);
        setContentView(binding.root);

       binding.root.setOnClickListener {
           startActivity(HomeWorkActivity::class.java)
       }
    }
}