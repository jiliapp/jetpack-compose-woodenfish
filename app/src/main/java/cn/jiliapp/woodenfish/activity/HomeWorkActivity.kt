package cn.jiliapp.woodenfish.activity

import android.animation.Animator
import android.os.Bundle
import cn.jiliapp.woodenfish.R
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.util.SparseArray
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.library.helper.DimenHelper
import cn.jiliapp.woodenfish.databinding.ActivityHomeWorkBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 做功课,得功德
 */
@AndroidEntryPoint
class HomeWorkActivity : ExtendAppCompatActivity() {


    //功德+1动画
    private val meritsPlusOneAnimatorSet = AnimatorSet()
    //木槌动画
    private lateinit var malletAnimator:ObjectAnimator;
    //木鱼声音id
    private var knockWoodenFishVoiceIds  = SparseArray<Int>().apply {
        append(R.raw.knock_wooden_fish,0)
        append(R.raw.knock_wooden_fish_2,0)
    };
    //声音播放器
    private  var soundPool= SoundPool.Builder().apply {
        //传入最多播放音频数量,
        this.setMaxStreams(knockWoodenFishVoiceIds.size())
        //AudioAttributes是一个封装音频各种属性的方法
        val attrBuilder = AudioAttributes.Builder()
        //设置音频流的合适的属性
        attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC)
        //加载一个AudioAttributes
        this.setAudioAttributes(attrBuilder.build())
    }.build();



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeWorkBinding.inflate(layoutInflater);
        setContentView(binding.root);
        toggleFullScreen();
        initSetting(binding);
        initOnClick(binding);
    }

    /**
     * 初始化设置 从本地加载功德值
     */
    private fun initSetting(binding:ActivityHomeWorkBinding){
        binding.myMerits.setText("111")

        //木槌
        binding.mallet.setPivotX(DimenHelper.dp2px(this, 81.8f) as Float)
        binding.mallet.setPivotY(DimenHelper.dp2px(this, 48.4f) as Float)
        malletAnimator = ObjectAnimator.ofFloat(binding.mallet, "rotation",0.0f, -10.0f, 0.0f).setDuration(313)

        //功德+1
        val alphaAnimator: Animator = ObjectAnimator.ofFloat( binding.meritsPlusOne, "alpha",1.0f, 0.0f)
        val transAnimator: Animator = ObjectAnimator.ofFloat( binding.meritsPlusOne, "translationY",0.0f, -70.0f)
        meritsPlusOneAnimatorSet.playTogether(alphaAnimator,transAnimator)
    }

    private val templeActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
        }
    }

    private val woodenFishActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
        }
    }



    /**
     * 初始化事件
     */
    private fun initOnClick(binding:ActivityHomeWorkBinding){
        // On the click of startBtn
        binding.root.setOnClickListener {
            malletAnimator.start();
            meritsPlusOneAnimatorSet.cancel();
            meritsPlusOneAnimatorSet.start();
            playWoodenFishKnockVoice(R.raw.knock_wooden_fish)
            //Caches.addGongde(this);
            //this.myGongde.setText(String.valueOf(Caches.getGongde(this)));
            // woodenFish();
        }


        binding.temple.setOnClickListener {
            startActivityResult(TempleActivity::class.java,templeActivityResultLauncher)
        }



        binding.woodenFish.setOnClickListener {

            startActivityResult(WoodenFishActivity::class.java,woodenFishActivityResultLauncher)
        }
    }

    /**
     * 播放木鱼敲击声音
     */
    private fun playWoodenFishKnockVoice(voiceResId:Int){
        if (soundPool==null){
            Toast.makeText(baseContext,"应用还未初始化,请稍后", Toast.LENGTH_SHORT).show();
            return;
        }else if (voiceResId<=0){
            Toast.makeText(baseContext,"音频资源错误", Toast.LENGTH_SHORT).show();
            return;
        }else if (knockWoodenFishVoiceIds.get(voiceResId)==0){
            soundPool.load(baseContext, voiceResId, 1);

            soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
                if (status == 0){
                    knockWoodenFishVoiceIds.put(voiceResId,sampleId);
                    soundPool.play(knockWoodenFishVoiceIds.get(voiceResId), 1f, 1f, 1, 0, 1f)
                }
            }
            return;
        }
        //第一个参数soundID
        //第二个参数leftVolume为左侧音量值（范围= 0.0到1.0）
        //第三个参数rightVolume为右的音量值（范围= 0.0到1.0）
        //第四个参数priority 为流的优先级，值越大优先级高，影响当同时播放数量超出了最大支持数时SoundPool对该流的处理
        //第五个参数loop 为音频重复播放次数，0为值播放一次，-1为无限循环，其他值为播放loop+1次
        //第六个参数 rate为播放的速率，范围0.5-2.0(0.5为一半速率，1.0为正常速率，2.0为两倍速率)
        soundPool.play(knockWoodenFishVoiceIds.get(voiceResId), 1f, 1f, 1, 0, 1f)
    }


}