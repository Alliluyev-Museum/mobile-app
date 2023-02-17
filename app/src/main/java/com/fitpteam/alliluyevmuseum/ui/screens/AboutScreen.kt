package com.fitpteam.alliluyevmuseum.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fitpteam.alliluyevmuseum.R

@Composable
fun AboutScreen(onBackPress: () -> Unit) {
    AboutWithTopBar(onBackPress)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AboutWithTopBar(onBackPress: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "О музее")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AboutInfo()
            }
        })
}

@Composable
private fun AboutInfo() {
    Text(
        text = stringResource(id = R.string.about_museum),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .padding(16.dp)
    )
}