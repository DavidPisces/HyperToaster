package com.reiraku.hypertoaster.entity

import com.reiraku.hypertoaster.bean.Left

class StringToastBean(
    private var left: Left? = null,
    private var right: Right? = null
) {
    fun getLeft(): Left {
        return left!!
    }

    fun setLeft(left: Left?) {
        this.left = left
    }

    fun getRight(): Right {
        return right!!
    }

    fun setRight(right: Right?) {
        this.right = right
    }

    fun getStringToastBundle(): StringToastBundle {
        return StringToastBundle()
    }
}