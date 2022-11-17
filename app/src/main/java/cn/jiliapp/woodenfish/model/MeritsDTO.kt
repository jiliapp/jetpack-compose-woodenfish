package cn.jiliapp.woodenfish.model

import java.util.Date

/**
 *  knock 敲击次数
 *  total 本次敲击次数
 *  time 敲击时间
 *  batchId 批次号
 *  sign 数据签名
 */
class MeritsDTO (var knock:Int,var batchId:Long) {

    var total:Int =0;
    var time:Date= Date();

    private lateinit var sign:String;
    init {

    }
    constructor(knock: Int, batchId: Long, total: Int) : this(knock, batchId) {
        //次构造函数初始化代码块
        this.total=total;
        this.sign="1111";
    }


}