package com.starkindustries.jetpackcomposeinternalstorage.Gallery

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun GalleryCompose(){

    var singleImageUri by remember{
        mutableStateOf<Uri?>(null)
    }

    var multipleImageUri by remember{
        mutableStateOf<List<Uri>>(emptyList())
    }

    var singleImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            singleImageUri = it
        })

    var multipleImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    , onResult = {
        singleImageUri=it
    })

    LazyColumn(modifier = Modifier
        .fillMaxSize()) {
        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
            , horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = {
                    singleImageLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Single Photo"
                    , fontSize = 18.sp
                    , fontWeight = FontWeight.W400)
                }
                Button(onClick = {
                    multipleImageLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Mulltiple Photos"
                    , fontSize = 18.sp
                    , fontWeight = FontWeight.W400)
                }

            }
        }
        item {
            AsyncImage(model = singleImageUri
                , contentDescription = null
            , modifier = Modifier
                    .fillMaxWidth()
            , contentScale = ContentScale.Crop)
        }
        items(multipleImageUri){uri->
            AsyncImage(model = uri
                , contentDescription = null
                , modifier = Modifier
                    .fillMaxWidth()
            , contentScale = ContentScale.Crop)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun GalleryPreview(){
    GalleryCompose()
}