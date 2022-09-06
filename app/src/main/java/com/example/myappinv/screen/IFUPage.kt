package com.example.myappinv.screen


import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

@Composable
fun ifuContent(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("IFU | WebView", color = Color.White) }, backgroundColor = Color(0xff0f9d58)) },
        content = { MyContentIfu() }
    )
}

@Composable
fun MyContentIfu() {
    val mUrl = "https://www.todoandroid.es/instrucciones-samsung-galaxy-note-edge/"
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}