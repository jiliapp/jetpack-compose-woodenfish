package cn.jiliapp.library.helper
import android.content.Context

 class DimenHelper {
     companion object {
         public fun dp2px(context: Context, value: Float): Float {
             return (context.resources.displayMetrics.density * value + 0.5f).toFloat()
         }

         public fun getStatusBarHeight(context: Context): Float {
             val resources = context.resources
             val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
             return if (resourceId > 0) {
                 resources.getDimensionPixelSize(resourceId) as Float
             } else Math.ceil((resources.displayMetrics.density * 24.0f).toDouble()).toFloat()
         }

     }



}