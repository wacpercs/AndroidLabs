package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

/**
 * Рамка картины с эффектом галерейной стены (Рис. 4.7)
 */
@Composable
fun ArtworkWall(
    @DrawableRes imageRes: Int,
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(4.dp))
            .border(width = 2.dp, color = Color(0xFFDDDDDD), shape = RoundedCornerShape(4.dp)),
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = titleRes),
            modifier = Modifier.padding(24.dp),
            contentScale = ContentScale.Fit
        )
    }
}

/**
 * Блок с описанием картины (название, автор и год) (Рис. 4.7)
 */
@Composable
fun ArtworkDescriptor(
    @StringRes titleRes: Int,
    @StringRes artistRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = titleRes),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = artistRes),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/**
 * Кнопки навигации Previous и Next
 */
@Composable
fun NavigationButtons(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.previous))
        }
        Spacer(modifier = Modifier.width(24.dp))
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}

/**
 * Главный экран приложения ArtSpace с адаптацией под широкий и портретный экран
 */
@Composable
fun ArtSpaceApp() {
    var currentArtwork by remember { mutableIntStateOf(1) }

    // Конструкция when для выбора текущей картины (Рис. 4.7)
    val (imageRes, titleRes, artistRes) = when (currentArtwork) {
        1 -> Triple(R.drawable.mona_lisa, R.string.artwork1_title, R.string.artwork1_artist)
        2 -> Triple(R.drawable.starry_night, R.string.artwork2_title, R.string.artwork2_artist)
        3 -> Triple(R.drawable.the_scream, R.string.artwork3_title, R.string.artwork3_artist)
        4 -> Triple(R.drawable.the_wave, R.string.artwork4_title, R.string.artwork4_artist)
        else -> Triple(R.drawable.mona_lisa, R.string.artwork1_title, R.string.artwork1_artist)
    }

    val onPrevious: () -> Unit = {
        currentArtwork = if (currentArtwork > 1) currentArtwork - 1 else 4
    }

    val onNext: () -> Unit = {
        currentArtwork = if (currentArtwork < 4) currentArtwork + 1 else 1
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(20.dp)
    ) {
        val isWideScreen = maxWidth > maxHeight

        if (isWideScreen) {
            // Альбомная / широкая ориентация экрана (картина слева, описание и кнопки справа)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ArtworkWall(
                    imageRes = imageRes,
                    titleRes = titleRes,
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight(0.85f)
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ArtworkDescriptor(
                        titleRes = titleRes,
                        artistRes = artistRes
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    NavigationButtons(
                        onPreviousClick = onPrevious,
                        onNextClick = onNext
                    )
                }
            }
        } else {
            // Портретная ориентация экрана
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                ArtworkWall(
                    imageRes = imageRes,
                    titleRes = titleRes,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                ArtworkDescriptor(
                    titleRes = titleRes,
                    artistRes = artistRes
                )
                Spacer(modifier = Modifier.height(24.dp))
                NavigationButtons(
                    onPreviousClick = onPrevious,
                    onNextClick = onNext,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
