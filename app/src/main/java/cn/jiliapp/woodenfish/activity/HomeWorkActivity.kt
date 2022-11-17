package cn.jiliapp.woodenfish.activity

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.util.SparseArray
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.IntegerRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Observer
import cn.jiliapp.library.activity.ExtendAppCompatActivity
import cn.jiliapp.library.helper.DimenHelper
import cn.jiliapp.woodenfish.R
import cn.jiliapp.woodenfish.databinding.ActivityHomeWorkBinding
import cn.jiliapp.woodenfish.model.MeritsDTO
import cn.jiliapp.woodenfish.viewmodel.DataStoreViewModel
import cn.jiliapp.woodenfish.viewmodel.HomeWorkViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

/**
 * 做功课,得功德
 */



@AndroidEntryPoint
class HomeWorkActivity : ExtendAppCompatActivity() {


    //功德+1动画
    private val meritsPlusOneAnimatorSet = AnimatorSet()
    //木槌动画
    private lateinit var malletAnimator:ObjectAnimator
    //木鱼声音id
    private var knockWoodenFishVoiceIds  = SparseArray<Int>().apply {
        append(R.raw.knock_wooden_fish,0)
        append(R.raw.knock_wooden_fish_2,0)
    }
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
    }.build()

    private  val homeWorkViewModel: HomeWorkViewModel by viewModels()
    private  val dataStoreViewModel: DataStoreViewModel by viewModels()

    private val batchId: Long=1111L;

    private var knockTimer: Timer? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeWorkBinding.inflate(layoutInflater)
        setContentView(binding.root);
        toggleFullScreen()
        initSetting(binding)
        initViewModel(binding)
        initOnClick(binding)
    }

    override fun onStart() {
        super.onStart()
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    /**
     * 初始化设置 从本地加载功德值
     */
    private fun initSetting(binding:ActivityHomeWorkBinding){
        //功德值
        MainScope().launch {
            binding.myMerits.text= dataStoreViewModel.merits.firstOrNull()?.knock.toString()
        }


        //木槌
        binding.mallet.pivotX = DimenHelper.dp2px(this, 81.8f) as Float
        binding.mallet.pivotY = DimenHelper.dp2px(this, 48.4f) as Float
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
        //功德佛
        /*binding.temple.setOnClickListener {
          startActivityResult(TempleActivity::class.java,templeActivityResultLauncher)
        }
*/
        //更换法器
        binding.instrument.setOnClickListener {
            startActivityResult(WoodenFishActivity::class.java,templeActivityResultLauncher)
        }

        //自动模式
        binding.autoKnock.setOnCheckedChangeListener { _, isChecked ->
            autoKnockKnock(isChecked)
        }

        //敲击木鱼
        binding.woodenFish.setOnClickListener {
            if (isAutoKnocking()){
                Toast.makeText(this,"自动累计功德中...",Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }
            knockKnock();
        }

        binding.root.setOnClickListener {
            if (isAutoKnocking()){
                Toast.makeText(this,"自动累计功德中...",Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }
            knockKnock();
        }

    }


    private fun autoKnockKnock(isChecked: Boolean){
        if (isChecked) {
            if (knockTimer!=null){
                knockTimer?.cancel()
                knockTimer = null
            }
            knockTimer= Timer()
            knockTimer?.schedule(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        knockKnock()
                    }
                }
            }, 0, 618)
        } else {
            //取消自动任务
            knockTimer?.cancel()
            knockTimer = null
        }
    }

    private fun  knockKnock(){
        malletAnimator.start();
        meritsPlusOneAnimatorSet.cancel();
        meritsPlusOneAnimatorSet.start();
        playWoodenFishKnockVoice(R.raw.knock_wooden_fish)
        //Caches.addGongde(this);
        //this.myGongde.setText(String.valueOf(Caches.getGongde(this)));
        // woodenFish();
        homeWorkViewModel.knockMerits(MeritsDTO(1,batchId))
    }


    private fun isAutoKnocking():Boolean{
        return knockTimer!==null;
    }

    private fun initViewModel(binding:ActivityHomeWorkBinding){
        homeWorkViewModel.knockMeritsList.observe(this, Observer{
            val v=binding.myMerits.text.toString();
            val i=v.toInt()
            val merits=i.plus( if(it.isEmpty())
                0
            else
                1)
            binding.myMerits.text = merits.toString()
            MainScope().launch {
                saveMerits(merits?:0)
            }
        })
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
        soundPool.play(knockWoodenFishVoiceIds.get(voiceResId), 1f, 1f, 1, 0, 1f)
    }


     private fun saveMerits(increment:Int) {
        dataStoreViewModel.saveMerits(MeritsDTO(increment,1L))
     }





}