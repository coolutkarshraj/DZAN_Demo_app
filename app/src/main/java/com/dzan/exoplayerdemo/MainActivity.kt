package com.dzan.exoplayerdemo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.dzan.exoplayerdemo.screens.MainScreen
import com.dzan.exoplayerdemo.screens.VideoScreen
import com.dzan.exoplayerdemo.ui.theme.DAZNDemoAppTheme
import com.google.android.exoplayer2.Player
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject


@Inject
lateinit var fireBaseModule : FirebaseAnalytics
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DAZNDemoAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(navController)
        }
        composable("video") {
           VideoScreen()
        }
    }
}

fun provideExoPlayer(context : Context, mediaItem: MediaItem): ExoPlayer {
    val player = ExoPlayer.Builder(context).build()
    player.setMediaItem(mediaItem)
    player.addListener(object : Player.Listener {
        override fun onPlaybackStateChanged(@Player.State state: Int) {
            val bundle: Bundle ? = null
            when (state) {
                Player.STATE_READY -> {
                    bundle?.putString(player.playbackState.toString(), player.currentTimeline.toString());
                    fireBaseModule.logEvent(player.playbackState.toString(), bundle)
                    // The player is able to immediately play from its current position.
                // The player will be playing if getPlayWhenReady() is true, and paused otherwise.
                }
                Player.STATE_BUFFERING -> {
                    bundle?.putString(player.playbackState.toString(), player.currentTimeline.toString());
                    fireBaseModule.logEvent(player.playbackState.toString(), bundle)
                    // The player is not able to immediately play the media,
                // but is doing work toward being able to do so. This state typically occurs when the player needs to buffer more data before playback can start.
                }
                Player.STATE_IDLE -> {
                    bundle?.putString(player.playbackState.toString(), player.currentTimeline.toString());
                    fireBaseModule.logEvent(player.playbackState.toString(), bundle)
                    // The player is idle, meaning it holds only limited resources.
                // The player must be prepared before it will play the media.
                }
                Player.STATE_ENDED -> {
                    bundle?.putString(player.playbackState.toString(), player.currentTimeline.toString());
                    fireBaseModule.logEvent(player.playbackState.toString(), bundle)
                    // The player has finished playing the media.
                }
                Player.COMMAND_PLAY_PAUSE ->{
                    // TCommand to start, pause or resume playback.
                    bundle?.putString(player.playbackState.toString(), player.currentTimeline.toString())
                    fireBaseModule.logEvent(player.playbackState.toString(), bundle)
                }
                Player.COMMAND_SEEK_FORWARD ->{
                    //Command to seek forward by a fixed increment inside the current
                    bundle?.putString(player.playbackState.toString(), player.currentTimeline.toString());
                    fireBaseModule.logEvent(player.playbackState.toString(), bundle)
                }
                Player.COMMAND_SEEK_BACK ->{
                    //Command to seek forward by a fixed increment inside the current
                    bundle?.putString(player.playbackState.toString(), player.currentTimeline.toString());
                    fireBaseModule.logEvent(player.playbackState.toString(), bundle)
                }
                else -> {
                // Other things
            }
            }
        }
    })
    return player
}