package com.example.android_new.tictactoe

import android.content.Context
import android.content.SharedPreferences
import com.example.android_new.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicTacToeSharedPref @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        private const val PREF_DATA = "data"
    }

    private val context: Context = context.applicationContext

    @Volatile
    private var sharedPref: SharedPreferences? = null

    private fun getSharedPerf(): SharedPreferences {
        return sharedPref ?: synchronized(this) {
            context.getSharedPreferences(
                "${BuildConfig.APPLICATION_ID}.main",
                Context.MODE_PRIVATE
            )
        }
    }

    // ----------------------------------------------------------------

    fun reset() {
        getSharedPerf().edit().clear().apply()
    }

    // ----------------------------------------------------------------

    fun setData(data: Set<String>) {
        getSharedPerf()
            .edit()
            .apply {
                putStringSet(PREF_DATA, data)
                apply()
            }
    }

    fun getData(): Set<String> {
        return getSharedPerf().getStringSet(PREF_DATA, emptySet()) ?: emptySet()
    }
}
