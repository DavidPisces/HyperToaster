package com.reiraku.hypertoaster.entity

class Right(
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