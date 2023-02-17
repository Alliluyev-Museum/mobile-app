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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitpteam.alliluyevmuseum.R
import com.fitpteam.alliluyevmuseum.models.Exhibit
import com.fitpteam.alliluyevmuseum.ui.theme.AlliluyevMuseumTheme
import com.fitpteam.alliluyevmuseum.ui.viewmodels.RoomViewModel

@Composable
fun RoomScreen(
    roomId: Int?,
    onRoomPress: (id: Int, name: String, description: String, roomId: Int) -> Unit,
    onBackPress: () -> Unit
) {
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
private fun RoomExhibitsWithTopBar(
    viewModel: RoomViewModel,
    onRoomPress: (id: Int, name: String, description: String, roomId: Int) -> Unit,
    onBackPress: () -> Unit
) {
    val exhibits = viewModel.exhibitList.value
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
                verticalArrangement = when (exhibits.isEmpty()) {
                    true -> Arrangement.Center
                    false -> Arrangement.Top
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ExhibitList(exhibits, onRoomPress)
            }
        })
}

@Composable
private fun ExhibitList(
    exhibits: List<Exhibit>,
    onRoomPress: (id: Int, name: String, description: String, roomId: Int) -> Unit
) {
    if (exhibits.isEmpty()) {
        Text(
            text = "Список пуст",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp)
        )
    } else {
        LazyColumn(
            state = rememberLazyListState()
        ) {
            items(exhibits) { exhibit ->
                ExhibitCard(exhibit, onRoomPress)
            }
        }
    }
}

@Composable
private fun ExhibitCard(
    exhibit: Exhibit,
    onRoomPress: (id: Int, name: String, description: String, roomId: Int) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 8.dp)
        .height(150.dp)
        .clickable {
            onRoomPress(
                exhibit.id,
                exhibit.name,
                exhibit.fullDescription,
                exhibit.roomId
            )
        }) {
        Row {
            val resources = LocalContext.current.resources
            val packageName = LocalContext.current.packageName
            var resourceId = remember {
                resources.getIdentifier(
                    "e${exhibit.id}_${exhibit.roomId}", "mipmap",
                    packageName
                )
            }
            if (resourceId == 0)
                resourceId = R.drawable.ic_baseline_no_photography_24

            Image(
                painter = painterResource(id = resourceId),
                contentDescription = exhibit.name,
                Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(0.dp, 0.dp, 8.dp)
            ) {
                Text(
                    exhibit.name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(exhibit.description)
            }
        }
    }
}