package com.fitpteam.alliluyevmuseum.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitpteam.alliluyevmuseum.models.Exhibit
import com.fitpteam.alliluyevmuseum.ui.theme.AlliluyevMuseumTheme
import com.fitpteam.alliluyevmuseum.ui.viewmodels.RoomViewModel

@Composable
fun RoomScreen(roomId: Int?, onRoomPress: (id: Int, name: String, description: String, roomId: Int) -> Unit, onBackPress: () -> Unit) {
    val owner = LocalViewModelStoreOwner.current
    owner?.let {
        val viewModel: RoomViewModel = viewModel(
            it,
            "RoomViewModel",
            RoomViewModel.Factory
        )
        viewModel.updateExhibitsList(roomId ?: 1)
        RoomExhibitsWithTopBar(viewModel, onRoomPress, onBackPress)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoomExhibitsWithTopBar(viewModel: RoomViewModel, onRoomPress: (id: Int, name: String, description: String, roomId: Int) -> Unit, onBackPress: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Список экспонатов")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
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
                ExhibitList(viewModel.exhibitList.value, onRoomPress) }
        })
}

@Composable
private fun ExhibitList(exhibits: List<Exhibit>, onRoomPress: (id: Int, name: String, description: String, roomId: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier,
        state = rememberLazyListState()
    ) {
        items(exhibits) { exhibit ->
            ExhibitCard(exhibit, onRoomPress)
        }
    }
}

@Composable
private fun ExhibitCard(exhibit: Exhibit, onRoomPress: (id: Int, name: String, description: String, roomId: Int) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 8.dp)
        .clickable { onRoomPress(exhibit.id, exhibit.name, exhibit.fullDescription, exhibit.roomId) } ) {
        Row {
            val resources = LocalContext.current.resources
            val resourceId: Int = resources.getIdentifier(
                "e${exhibit.id}_${exhibit.roomId}_1", "mipmap",
                LocalContext.current.packageName
            )
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = exhibit.name,
                Modifier.weight(1f),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Column(modifier = Modifier
                .weight(2f)
                .padding(0.dp, 0.dp, 8.dp)) {
                Text(
                    exhibit.name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(exhibit.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoomPreview() {
    AlliluyevMuseumTheme {
        ExhibitList(
            listOf(
                Exhibit(
                    1,
                    "Стул",
                    "Это крутой стул",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                ),
                Exhibit(
                    2,
                    "Стул",
                    "Это крутой стул",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                ),
            )
        ) { a, b, c, d -> }
    }
}