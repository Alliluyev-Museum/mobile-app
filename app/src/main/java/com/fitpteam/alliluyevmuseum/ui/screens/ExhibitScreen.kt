package com.fitpteam.alliluyevmuseum.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.fitpteam.alliluyevmuseum.R
import com.fitpteam.alliluyevmuseum.models.Exhibit

private val headerHeight = 250.dp
private val toolbarHeight = 56.dp

private val paddingMedium = 16.dp

private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 72.dp

private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
fun ExhibitScreen(
    exhibitId: Int?,
    exhibitName: String?,
    exhibitDescription: String?,
    exhibitRoomId: Int?,
    onBackPress: () -> Unit
) {
    CollapsingToolbarParallaxEffect(
        Exhibit(
        exhibitId ?: 0,
        exhibitName ?: "Нет названия",
        "",
        exhibitDescription ?: "Нет описания",
            exhibitRoomId ?: 0
    ), onBackPress)
}

@Composable
fun CollapsingToolbarParallaxEffect(exhibit: Exhibit, onBackPress: () -> Unit) {
    val scroll: ScrollState = rememberScrollState(0)

    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(exhibit, scroll, headerHeightPx)
        Body(exhibit, scroll)
        Toolbar(scroll, headerHeightPx, toolbarHeightPx, onBackPress)
        Title(exhibit, scroll, headerHeightPx, toolbarHeightPx)
    }
}

@Composable
private fun Header(exhibit: Exhibit, scroll: ScrollState, headerHeightPx: Float) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(headerHeight)
        .graphicsLayer {
            translationY = -scroll.value.toFloat() / 2f // Parallax effect
            alpha = (-1f / headerHeightPx) * scroll.value + 1
        }
    ) {
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
            contentDescription = "",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4 // Gradient applied to wrap the title only
                    )
                )
        )
    }
}

@Composable
private fun Body(exhibit: Exhibit, scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(headerHeight))
        repeat(5) {
            Text(
                text = exhibit.fullDescription,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(scroll: ScrollState, headerHeightPx: Float, toolbarHeightPx: Float, onBackPress: () -> Unit) {
    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val showToolbar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom
        }
    }

    AnimatedVisibility(
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
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
            },
            title = {},
        )
    }
}

@Composable
private fun Title(
    exhibit: Exhibit,
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float
) {
    var titleHeightPx by remember { mutableStateOf(0f) }
    var titleWidthPx by remember { mutableStateOf(0f) }

    Text(
        text = exhibit.name,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .padding(top = 2.dp)
            .graphicsLayer {
                val collapseRange: Float = (headerHeightPx - toolbarHeightPx)
                val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)

                val scaleXY = lerp(
                    titleFontScaleStart.dp,
                    titleFontScaleEnd.dp,
                    collapseFraction
                )

                val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f

                val titleYFirstInterpolatedPoint = lerp(
                    headerHeight - titleHeightPx.toDp() - paddingMedium,
                    headerHeight / 2,
                    collapseFraction
                )

                val titleXFirstInterpolatedPoint = lerp(
                    titlePaddingStart,
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    collapseFraction
                )

                val titleYSecondInterpolatedPoint = lerp(
                    headerHeight / 2,
                    toolbarHeight / 2 - titleHeightPx.toDp() / 2,
                    collapseFraction
                )

                val titleXSecondInterpolatedPoint = lerp(
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    titlePaddingEnd - titleExtraStartPadding,
                    collapseFraction
                )

                val titleY = lerp(
                    titleYFirstInterpolatedPoint,
                    titleYSecondInterpolatedPoint,
                    collapseFraction
                )

                val titleX = lerp(
                    titleXFirstInterpolatedPoint,
                    titleXSecondInterpolatedPoint,
                    collapseFraction
                )

                translationY = titleY.toPx()
                translationX = titleX.toPx()
                scaleX = scaleXY.value
                scaleY = scaleXY.value
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
                titleWidthPx = it.size.width.toFloat()
            }
    )
}