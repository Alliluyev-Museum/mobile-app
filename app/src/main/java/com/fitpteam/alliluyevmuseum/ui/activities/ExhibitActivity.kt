package com.fitpteam.alliluyevmuseum.ui.activities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fitpteam.alliluyevmuseum.R
import com.fitpteam.alliluyevmuseum.models.Exhibit
import com.fitpteam.alliluyevmuseum.ui.theme.AlliluyevMuseumTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExhibitActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val exhibitName = intent.getStringExtra(getString(R.string.exhibit_name_key))
            ?: getString(R.string.empty_exhibit_name)
        val exhibitDescription = intent.getStringExtra(getString(R.string.exhibit_description_key))
            ?: getString(R.string.empty_exhibit_description)
        val exhibit = Exhibit(exhibitName, "", exhibitDescription)
        setContent {
            AlliluyevMuseumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExhibitInfo(exhibit)
                }
            }
        }
    }
}

@Composable
private fun ExhibitInfo(exhibit: Exhibit) {
    Column(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 260.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = exhibit.name,
            contentScale = ContentScale.FillWidth
        )
        Column(modifier = Modifier.padding(all = 8.dp)) {
            Text(
                exhibit.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                exhibit.fullDescription,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExhibitPreview() {
    AlliluyevMuseumTheme {
        ExhibitInfo(
            Exhibit(
                "Стул",
                "",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            )
        )
    }
}