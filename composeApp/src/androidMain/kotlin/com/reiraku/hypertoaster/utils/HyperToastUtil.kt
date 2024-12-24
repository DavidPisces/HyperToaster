package com.reiraku.hypertoaster.utils

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import com.google.gson.Gson
import com.reiraku.hypertoaster.bean.Left
import com.reiraku.hypertoaster.entity.IconParams
import com.reiraku.hypertoaster.entity.Right
import com.reiraku.hypertoaster.entity.StringToastBean
import com.reiraku.hypertoaster.entity.StringToastBundle
import com.reiraku.hypertoaster.entity.TextParams
import kotlin.random.Random
import kotlin.time.Duration

class HyperToastUtil {
    fun newIconParams(
        category: String?,
        iconResName: String?,
        iconType: Int,
        iconFormat: String?
    ): IconParams {
        val params = IconParams(category, iconFormat, iconResName, iconType)
        return params
    }

    fun getRandomColor(): Int {
        val red = Random.nextInt(0, 256)  // 随机生成红色通道值
        val green = Random.nextInt(0, 256)  // 随机生成绿色通道值
        val blue = Random.nextInt(0, 256)  // 随机生成蓝色通道值
        return Color.rgb(red, green, blue) // 返回一个RGB颜色
    }

    @SuppressLint("WrongConstant")
    fun showStringToast(context: Context, text: String, color: Int?, duration: Long, isEmpty: Boolean = false) {
        try {
            var currentColor: Int = 114514
            val iconParams: IconParams?
            iconParams = if (!isEmpty) {
                IconParams(Category.DRAWABLE, FileType.SVG, "ic_toast", 0)
            }else{
                null
            }

            if (color == null) {
                currentColor = getRandomColor()
            }else{
                currentColor = color
            }

            val textParams = TextParams(text, currentColor)

            val left = Left(iconParams, textParams)
            val right = Right(iconParams, textParams)
            val stringToastBean = StringToastBean(left, right)

            val gson = Gson()
            val str = gson.toJson(stringToastBean)
            val bundle = StringToastBundle.Builder()
                .setPackageName(context.packageName)
                .setStrongToastCategory(StrongToastCategory.TEXT_BITMAP.value)
                .setTarget(null as PendingIntent?)
                .setLevel(0.0f)
                .setRapidRate(0.0f)
                .setDuration(duration)
                .setCharge(null)
                .setStringToastChargeFlag(0)
                .setParam(str)
                .setStatusBarStrongToast("show_custom_strong_toast")
                .onCreate()

            val service = context.getSystemService(Context.STATUS_BAR_SERVICE)

            service.javaClass.getMethod(
                "setStatus",
                Int::class.javaPrimitiveType,
                String::class.java,
                Bundle::class.java
            ).invoke(service, 1, "strong_toast_action", bundle)

        } catch (e: Exception) {
        }
    }


    object Category {
        const val RAW = "raw"
        const val DRAWABLE = "drawable"
        const val FILE = "file"
        const val MIPMAP = "mipmap"
    }

    object FileType {
        const val MP4 = "mp4"
        const val PNG = "png"
        const val SVG = "svg"
    }

    fun colorToInt(color: String?): Int {
        val color1 = Color.parseColor(color)
        val color2 = Color.parseColor("#FFFFFF")
        val color3 = color1 xor color2
        return color3.inv()
    }

    enum class StrongToastCategory(var value: String) {
        VIDEO_TEXT("video_text"),
        VIDEO_BITMAP_INTENT("video_bitmap_intent"),
        TEXT_BITMAP("text_bitmap"),
        TEXT_BITMAP_INTENT("text_bitmap_intent"),
        VIDEO_TEXT_TEXT_VIDEO("video_text_text_video")
    }
}