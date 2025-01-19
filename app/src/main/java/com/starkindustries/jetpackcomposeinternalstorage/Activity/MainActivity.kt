package com.starkindustries.jetpackcomposeinternalstorage.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.starkindustries.jetpackcomposeinternalstorage.Gallery.GalleryCompose
import com.starkindustries.jetpackcomposeinternalstorage.Gallery.SingleImageCompose
import com.starkindustries.jetpackcomposeinternalstorage.ui.theme.JetpackComposeInternalStorageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeInternalStorageTheme {
                SingleImageCompose()
            }
        }
    }
}



