package com.reiraku.hypertoaster.entity

import android.app.PendingIntent
import android.os.Bundle

class StringToastBundle {
    private var mBundle: Bundle = Bundle()
    private var mPackageName: String? = null
    private var mCategory: String? = null
    private var mIntent: PendingIntent? = null
    private var mDuration: Long = 0
    private var mLevel = 0f
    private var mRate = 0f
    private var mCharge: String? = null
    private var mFlag = 0
    private var mParam: String? = null
    private var mStatus: String? = null

    class Builder {
        private var mBundle: Bundle = Bundle()

        fun setPackageName(name: String?): Builder {
            mBundle.putString("package_name", name)
            return this
        }

        fun setStrongToastCategory(category: String?): Builder {
            mBundle.putString("strong_toast_category", category)
            return this
        }

        fun setTarget(intent: PendingIntent?): Builder {
            mBundle.putParcelable("target", intent)
            return this
        }

        fun setDuration(duration: Long): Builder {
            mBundle.putLong("duration", duration)
            return this
        }

        fun setLevel(level: Float): Builder {
            mBundle.putFloat("level", level)
            return this
        }

        fun setRapidRate(rate: Float): Builder {
            mBundle.putFloat("rapid_rate", rate)
            return this
        }

        fun setCharge(charge: String?): Builder {
            mBundle.putString("charge", charge)
            return this
        }

        fun setStringToastChargeFlag(flag: Int): Builder {
            mBundle.putInt("string_toast_charge_flag", flag)
            return this
        }

        fun setParam(param: String?): Builder {
            mBundle.putString("param", param)
            return this
        }

        fun setStatusBarStrongToast(status: String?): Builder {
            mBundle.putString("status_bar_strong_toast", status)
            return this
        }

        fun onCreate(): Bundle {
            return mBundle
        }
    }
}
