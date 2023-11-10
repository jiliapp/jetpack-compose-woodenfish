package com.bbbang.woodenfish.ui.sense

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bbbang.woodenfish.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "wooden_fish")
val meritsKey = intPreferencesKey("merits")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoodenFishScreen(){

    val materialBlue700= Color(0xFF1976D2)
    val colors= TopAppBarDefaults.smallTopAppBarColors(
        containerColor=materialBlue700
        ,titleContentColor=Color.White)

    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "电子木鱼", modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center))
                    }
                ,colors=colors)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            WoodenFish(R.drawable.temple_sjml,snackBarHostState)
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun WoodenFish(resId:Int,snackBarHostState :SnackbarHostState ,modifier: Modifier = Modifier){
    val context = LocalContext.current

    val autoMeritState = remember { mutableStateOf(false) }
    val meritState = remember { mutableStateOf(0) }

    var visibleState by remember { mutableStateOf(false) }

    val rotationAngle by animateFloatAsState(
        targetValue = if (!visibleState) 15f else 0f,
        animationSpec = tween(durationMillis = 313),label = "木槌动画"
    )

    val scope = rememberCoroutineScope()


    // 创建 SoundPool 对象
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .build()
            )
            .build()
    }

    // 加载音效资源
    val soundId = remember {
        soundPool.load(context, R.raw.knock_wooden_fish, 1)
    }

        ConstraintLayout(modifier= modifier
            .fillMaxSize()) {
            val (switch,background,autoLabel,middle,meritPlus1,merit,woodenFish,mallet) = createRefs()
            //background
            Text(text = stringResource(id = R.string.words),
                fontSize = 40.sp,
                lineHeight=41.sp,
                overflow = TextOverflow.Ellipsis,
                color=Color(0xff2c2c2c),
                modifier= Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF05060A))
            )

            Switch(checked = autoMeritState.value, onCheckedChange = {
                autoMeritState.value=it
            },
                colors = SwitchDefaults.colors(
                    checkedThumbColor =  Color(205, 175, 0), // 切换开启时的拇指颜色
                    checkedTrackColor = Color(245, 215, 0), // 切换开启时的轨道颜色
                    uncheckedThumbColor = Color.Gray, // 切换关闭时的拇指颜色
                    uncheckedTrackColor = Color.LightGray // 切换关闭时的轨道颜色
                ),
                modifier = Modifier.constrainAs(switch){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(parent.top,16.dp)
                },
            )

            Text(text = "自动敲木鱼",
                color=Color.White,
                modifier=Modifier.constrainAs(autoLabel){
                start.linkTo(switch.end,8.dp)
                bottom.linkTo(switch.bottom)
                top.linkTo(switch.top)
            })


            Spacer(modifier = Modifier.size(8.dp))


            Image(painter = painterResource(id = resId)
                ,contentDescription =null
                ,modifier = Modifier
                    .safeDrawingPadding()
                    .constrainAs(middle) {
                        top.linkTo(switch.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })


            Image(painter = painterResource(id = R.drawable.wooden_fish)
                ,contentDescription =null
                ,modifier = Modifier
                    .safeDrawingPadding()
                    .clickable {
                        if (autoMeritState.value){
                            scope.launch {
                               when (snackBarHostState.showSnackbar("自动加功德中...","确认",false, SnackbarDuration.Short)) {
                                    SnackbarResult.ActionPerformed -> {
                                        /* Handle snackbar action performed */
                                    }
                                    SnackbarResult.Dismissed -> {
                                        /* Handle snackbar dismissed */
                                    }
                                }

                            }
                           return@clickable
                        }
                        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
                        meritState.value++
                        visibleState = true
                    }
                    .constrainAs(woodenFish) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, 16.dp)
                    })

            //val rotationCenter = Offset(x = 100.00f, y = 100f)

            Image(painter = painterResource(id = R.drawable.mallet)
                ,contentDescription =null
                ,modifier = Modifier
                    .safeDrawingPadding()
                    .constrainAs(mallet) {
                        top.linkTo(woodenFish.top, (-16).dp)
                        end.linkTo(woodenFish.end, (-16).dp)
                    }
                    //https://developer.android.com/jetpack/compose/graphics/draw/modifiers?hl=zh-cn
                    .graphicsLayer {
                        //TransformOrigin.Center TransformOrigin(0f, 0f)
                        this.transformOrigin = TransformOrigin(0.7f, 0.7f)
                        this.rotationZ = rotationAngle
                    }
            )


            AnimatedVisibility(
                visible=visibleState,
                exit = fadeOut(),
                enter = fadeIn()+ slideInVertically { fullHeight->fullHeight },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(meritPlus1) {
                        bottom.linkTo(mallet.top,16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = "功德+1",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                        .graphicsLayer {
                            //alpha = animatedAlpha.value
                            //translationY = animatedOffset.value
                        }
                )
            }

            Text(text = "${meritState.value}",
                color=Color.White,
                modifier=Modifier
                .constrainAs(merit){
                    bottom.linkTo(mallet.top,64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, fontSize = 36.sp, fontWeight = FontWeight.Black
            )

            LaunchedEffect(meritState.value){
                if (visibleState) {
                    delay(313)
                    visibleState = false
                }
                if (meritState.value<=0){
                    //读取数据
                    val exampleCounterFlow: Flow<Int> = context.dataStore.data
                        .map { preferences ->
                            preferences[meritsKey] ?: meritState.value
                        }
                    exampleCounterFlow.collect {
                        meritState.value=it
                    }
                }else{
                    //存储数据
                    context.dataStore.edit { settings ->
                        var currentCounterValue = settings[meritsKey] ?: 0
                        if (currentCounterValue<meritState.value){
                            currentCounterValue=meritState.value
                            settings[meritsKey] = currentCounterValue
                        }
                    }
                }
            }


            LaunchedEffect(autoMeritState.value) {
                if (autoMeritState.value) {
                    while (true) {
                        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
                        meritState.value++
                        visibleState=true
                        delay(1000)
                    }
                }


            }

        }



}



@Preview(showBackground = true)
@Composable
fun WoodenFishPreview(){
    val snackBarHostState = remember { SnackbarHostState() }
    WoodenFish(R.drawable.temple_sjml,snackBarHostState)
}