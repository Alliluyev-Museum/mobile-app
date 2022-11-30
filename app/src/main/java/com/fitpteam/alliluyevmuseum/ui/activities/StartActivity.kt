package com.fitpteam.alliluyevmuseum.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.fitpteam.alliluyevmuseum.ui.theme.AlliluyevMuseumTheme

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlliluyevMuseumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
        installData()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun installData(){
        // for each photo of room / exhibit
        val someImage = ImageBitmap(100, 100).asAndroidBitmap()
        openFileOutput("file.jpg", MODE_PRIVATE).use { stream ->
            if (!someImage.compress(Bitmap.CompressFormat.JPEG, 95, stream))
                throw Exception()
        }
    }
}