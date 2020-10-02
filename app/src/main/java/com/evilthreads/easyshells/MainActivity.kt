package com.evilthreads.easyshells

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {
    init {
        lifecycleScope.launchWhenCreated {
            val executeCommand = async { shell("uptime") }
            Toast.makeText(this@MainActivity, executeCommand.await(), Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}