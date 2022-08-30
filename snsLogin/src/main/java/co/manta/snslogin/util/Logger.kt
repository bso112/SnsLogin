package co.manta.snslogin.util

import android.util.Log

internal object Logger {
    private const val TAG = "MANTA_FIREBASE_AUTH"

    fun d(msg: String) {
        Log.d(TAG, msg)
    }

    fun e(msg: String) {
        Log.e(TAG, msg)
    }

    fun e(t: Throwable) {
        Log.e(TAG, t.message.toSafe())
    }

}