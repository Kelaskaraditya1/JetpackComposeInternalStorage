package com.starkindustries.jetpackcomposeinternalstorage.Gallery

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SingleImageCompose(){
    var singleImageUri by remember{
        mutableStateOf<Uri?>(null)
    }

    var galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {uri->
        singleImageUri=uri
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 20.dp)) {
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
            , contentAlignment = Alignment.Center){
                Button(onClick = {
                    galleryLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
                    , shape = RectangleShape) {
                    Text(text = "Gallery")
                }
            }
        }
        item{
            AsyncImage(model = singleImageUri
                , contentDescription =""
            , modifier = Modifier
                    .fillMaxWidth()
            , contentScale = ContentScale.Crop)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Preview(){
SingleImageCompose()
}