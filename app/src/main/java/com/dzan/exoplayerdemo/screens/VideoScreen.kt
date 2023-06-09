package com.dzan.exoplayerdemo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.dzan.exoplayerdemo.di.FirebaseModule
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.dzan.exoplayerdemo.provideExoPlayer
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VideoScreen() {
    val playWhenReady by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/wvmedia/clear/h264/tears/tears.mpd")
    val playerView = PlayerView(context)
    val player = provideExoPlayer(context = context, mediaItem = mediaItem)
    playerView.player = player

    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                playerView
            }
        )
    }
}