package com.manta.snslogin.common

import android.util.Log

object SnsLoginLogger {
    private const val TAG = "SnsLogin"

    fun d(msg: String) {
        onDebug {
            Log.d(TAG, msg)
        }
    }

    fun e(msg: String) {
        onDebug {
            Log.e(TAG, msg)
        }
    }

    fun e(t: Throwable) {
        onDebug {
            Log.e(TAG, t.message.orEmpty())
        }
    }

    private fun onDebug(block: () -> Unit) {
        onDebug(block)
    }

}