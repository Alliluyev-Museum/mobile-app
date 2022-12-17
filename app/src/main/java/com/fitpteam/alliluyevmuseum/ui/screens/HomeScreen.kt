package com.fitpteam.alliluyevmuseum.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitpteam.alliluyevmuseum.R

@Composable
fun HomeScreen(onNavigateToRoom: (roomId: Int) -> Unit) {
    HomeScreenWithTopBar(onNavigateToRoom)
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenWithTopBar(onNavigateToRoom: (roomId: Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Музей-квартира Аллилуевых")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                actions = {
                    TopAppBarDropdownMenu(mutableStateOf("Menu"))
                }
            )
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoomGrid(onNavigateToRoom)
            }
        })
}

@Composable
private fun RoomGrid(onNavigateToRoom: (roomId: Int) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 32.dp)) {
        Text(text = "Выберите комнату, чтобу увидеть список экспонатов",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp)
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
            ) {
                Button(
                    { onNavigateToRoom(1) }, Modifier
                        .weight(0.33f)
                        .fillMaxWidth()
                        .padding(0.dp)
                        .border(2.dp, Color.Black, RectangleShape),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        "Комната сестёр",
                        style = TextStyle(fontSize = 24.sp, color = Color.Black),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    { onNavigateToRoom(2) }, Modifier
                        .weight(0.33f)
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, RectangleShape),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        "Cтоловая",
                        style = TextStyle(fontSize = 24.sp, color = Color.Black),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    { onNavigateToRoom(3) },
                    Modifier
                        .weight(0.33f)
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, RectangleShape),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        "Экспозиционная",
                        style = TextStyle(fontSize = 24.sp, color = Color.Black),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
            ) {
                Button(
                    { onNavigateToRoom(4) }, Modifier
                        .weight(0.37f)
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, RectangleShape),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        "Комната В.И. Ленина",
                        style = TextStyle(fontSize = 24.sp, color = Color.Black),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    { onNavigateToRoom(5) }, Modifier
                        .weight(0.28f)
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                        .border(2.dp, Color.Black, RectangleShape),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        "Ванная",
                        style = TextStyle(fontSize = 24.sp, color = Color.Black),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    { onNavigateToRoom(6) }, Modifier
                        .weight(0.33f)
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                        .border(2.dp, Color.Black, RectangleShape),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        "Кухня",
                        style = TextStyle(fontSize = 24.sp, color = Color.Black),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBarDropdownMenu(bodyContent: MutableState<String>) {
    val expanded = remember { mutableStateOf(false) } // 1
    Box(
        Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = {
            expanded.value = true
            bodyContent.value = "Меню открываетсяя"
        }) {
            Icon(
                Icons.Filled.MoreVert,
                contentDescription = "Меню"
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(onClick = {
            expanded.value = false
        }, text = { Text("О музее", style = TextStyle(fontSize = 18.sp)
        ) })
    }
}

@Preview
@Composable
private fun MainPreview() {
    HomeScreenWithTopBar { }
}
