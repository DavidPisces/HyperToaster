package com.reiraku.hypertoaster.entity

class TextParams(
    private var text:String?,
    private var textColor: Int =0
){
    fun setText(text: String?) {
        this.text = text
    }

    fun setTextColor(textColor: Int) {
        this.textColor = textColor
    }

    fun getText(): String {
        return text!!
    }

    fun getTextColor(): Int {
        return textColor
    }

    override fun toString(): String {
        return "TextParams(text=$text, textColor=$textColor)"
    }

}