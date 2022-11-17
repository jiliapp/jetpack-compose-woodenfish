## 每天敲敲敲,烦恼忘掉

基于mvvm模式开发的木鱼应用

技术使用框架
1. hilt 依赖注入
2. viewmodel vm模块
3. $lifecycle 生命周期
4. okhttp/retrofit 网络访问
5. datastore 数据存储
6. lottie 动画播放
```
   implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.navigation:navigation-fragment-ktx:$lifecycle_version"
    implementation "androidx.navigation:navigation-ui-ktx:$lifecycle_version"
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    //hilt
    implementation "com.google.dagger:hilt-android:$hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:$hilt_version"


    //okhttp
    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:$okhttp_version"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    testImplementation("com.squareup.okhttp3:mockwebserver:$okhttp_version")
    implementation "org.conscrypt:conscrypt-android:2.5.2"
    //retrofit
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
    //datastore preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    //
    implementation "com.airbnb.android:lottie:$lottie_version"

```