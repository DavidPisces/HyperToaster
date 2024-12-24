package com.reiraku.hypertoaster.bean

import com.reiraku.hypertoaster.entity.IconParams
import com.reiraku.hypertoaster.entity.TextParams

class Left(
    private var iconParams: IconParams? = null,
    private var textParams: TextParams? = null
) {
    fun setIconParams(iconParams: IconParams?) {
        this.iconParams = iconParams
    }

    fun setTextParams(textParams: TextParams?) {
        this.textParams = textParams
    }

    fun getIconParams(): IconParams {
        return iconParams!!
    }

    fun getTextParams(): TextParams {
        return textParams!!
    }
}