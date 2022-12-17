package com.fitpteam.alliluyevmuseum.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitpteam.alliluyevmuseum.models.Exhibit
import com.fitpteam.alliluyevmuseum.ui.theme.AlliluyevMuseumTheme

@Composable
fun ExhibitScreen(
    exhibitId: Int?,
    exhibitName: String?,
    exhibitDescription: String?,
    exhibitRoomId: Int?,
    onBackPress: () -> Unit
) {
    ExhibitWithTopBar(
        Exhibit(
        exhibitId ?: 0,
        exhibitName ?: "Нет названия",
        "",
        exhibitDescription ?: "Нет описания",
            exhibitRoomId ?: 0
    ), onBackPress)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ExhibitWithTopBar(exhibit: Exhibit, onBackPress: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(exhibit.name)
                },
                navigationIcon = {
                        IconButton(onClick = onBackPress) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                })
        },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it)
            ) {
                Text(
                    exhibit.fullDescription,
                    Modifier.padding(all = 8.dp),
                    fontSize = 20.sp,
                )
            }
        }
    )
}

private val headerHeight = 275.dp
@Composable
private fun Header() {
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    Box(modifier = Modifier.fillMaxWidth().height(headerHeight)) {
//        Image(
//            painter = painterResource(id = R.drawable.),
//            contentDescription = "",
//            contentScale = ContentScale.FillBounds
//        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4 // to wrap the title only
                    )
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExhibitPreview() {
    AlliluyevMuseumTheme {
        ExhibitWithTopBar(
            Exhibit(
                1,
            "Стул",
                "",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            )
        ) { }
    }
}