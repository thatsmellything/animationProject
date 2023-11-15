package com.example.animationproject.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animationproject.ui.components.Loader
import com.example.animationproject.ui.theme.GesturesTheme

@Composable
fun RootNavigation() {
    Column(modifier = Modifier.fillMaxSize()) {

        //Loader()
        //Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
        GesturesTheme()
    }

}