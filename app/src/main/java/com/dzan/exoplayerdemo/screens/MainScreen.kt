package com.dzan.exoplayerdemo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dzan.exoplayerdemo.ui.theme.DAZNDemoAppTheme

@Composable
fun MainScreen(navController : NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "DAZN DEMO APP",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align= Alignment.CenterHorizontally)
                    )
                },

            )

        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                onClick = {
                    navController.navigate("video")
                }
            ) {
                Text(text = "Play Video with URL")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    DAZNDemoAppTheme {
        MainScreen(navController)
    }
}