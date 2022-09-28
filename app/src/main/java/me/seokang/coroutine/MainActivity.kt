package me.seokang.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blocking()

        Log.d(TAG, "onCreate: ")
    }

    private fun blocking() = runBlocking {
        launch {
            Log.d(TAG, "main: ${delayMethod(10_000L)}")
        }

        withContext(Dispatchers.Default) {
            Log.d(TAG, "main: ${delayMethod(5_000L)}")
        }

        Log.d(TAG, "main: finish")
    }

    private fun beforeDelay(): String {
        return "before delay"
    }
    private suspend fun delayMethod(value: Long): String {
        delay(value)
        return "delay-${value} finish"
    }
}