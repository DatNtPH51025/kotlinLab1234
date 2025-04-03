package com.poly.lab1234_ph51025.Lab6

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


data class Movie(
    val title: String,
    val duration: String,
    val date: String,
    val genre: String,
    val img: String,
)

data class Seat(
    var row: Char, val number: Int, var status:
    SeatStatus
)

enum class SeatStatus { EMPTY, SELECTED, BOOKED, AISLE }


val sampleMovies = listOf(
    Movie(
        "Dune: Hành Tinh Cát - Phần Hai",
        "187'",
        "29/02/2024",
        "Hành Động, Khoa Học Viễn Tưởng",
        "https://ca-times.brightspotcdn.com/dims4/default/9bacbe7/2147483647/strip/true/crop/5007x2420+0+0/resize/1200x580!/quality/75/?url=https%3A%2F%2Fcalifornia-times-brightspot.s3.amazonaws.com%2F60%2Ffa%2F34b8687b4dc8902a6a81159e873f%2Fla-en-dune-movie-one-shot-040.JPG"
    ),
    Movie(
        "Kung Fu Panda",
        "90'",
        "01/03/2024",
        "Hành động, hài hước",
        "https://upload.wikimedia.org/wikipedia/vi/1/18/Kung_fu_panda_poster.jpg"
    ),
    Movie(
        "Transformer",
        "150'",
        "29/02/2023",
        "Hành Động, Khoa Học Viễn Tưởng",
        "https://upload.wikimedia.org/wikipedia/vi/6/66/Transformers07.jpg"
    ),
    Movie(
        "Mai",
        "145'",
        "01/03/2024",
        "Tâm Lý, Tình Cảm",
        "https://cinema.momocdn.net/img/30196263872528348-thumb.jpg"
    ),
)



@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = movie.img,
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "Thời lượng: ${movie.duration}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Khởi chiếu: ${movie.date}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Thể loại: ${movie.genre}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}



@Composable
fun MovieListRow(movies: List<Movie>) {
    LazyRow {
        items(movies) { movie ->
            MovieCard(movie)
        }
    }
}

@Composable
fun MovieListColumn(movies: List<Movie>) {
    LazyColumn {
        items(movies) { movie ->
            MovieCard(movie)
        }
    }
}

@Composable
fun MovieGrid(movies: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies) { movie ->
            MovieCard(movie)
        }
    }
}

@Preview
@Composable
fun MainScreen1() {
    var viewMode by remember { mutableStateOf("row") }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { viewMode = "row" }) {
                Text("Hàng")
            }
            Button(onClick = { viewMode = "column" }) {
                Text("Cột")
            }
            Button(onClick = { viewMode = "grid" }) {
                Text("Lưới")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (viewMode) {
            "row" -> MovieListRow(movies = sampleMovies)
            "column" -> MovieListColumn(movies = sampleMovies)
            "grid" -> MovieGrid(movies = sampleMovies)
        }
    }
}

@Composable
fun SeatComposable(seat: Seat, clickable: Boolean = true) {
    var status by remember { mutableStateOf(seat.status) }
    val backgroundColor = when (status) {
        SeatStatus.EMPTY -> Color.LightGray.copy(alpha = 0.5f)
        SeatStatus.SELECTED -> Color(0xFFFFA500)
        SeatStatus.BOOKED -> Color.Gray
        SeatStatus.AISLE -> Color.Transparent
    }
    val borderModifier = if (status != SeatStatus.AISLE) {
        Modifier.border(
            BorderStroke(
                1.dp, Color.DarkGray.copy(
                    alpha =
                    0.8f
                )
            ),
            shape = RoundedCornerShape(8.dp)
        )
    } else Modifier
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(width = 35.dp, height = 30.dp)
            .then(borderModifier)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(
                if (seat.status != SeatStatus.AISLE) 3.dp
                else 0.dp
            )
            .clickable(
                enabled = clickable && (status ==
                        SeatStatus.EMPTY || status == SeatStatus.SELECTED)
            ) {
                status = if (status == SeatStatus.EMPTY)
                    SeatStatus.SELECTED else SeatStatus.EMPTY
            },
        contentAlignment = Alignment.Center
    ) {
        if (seat.status != SeatStatus.AISLE) {
            Text(
                text = "${seat.row}${seat.number}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun PreviewSeat() {
    Row {
        SeatComposable(Seat('X', 1, SeatStatus.EMPTY))
        SeatComposable(Seat('Y', 1, SeatStatus.SELECTED))
        SeatComposable(Seat('Z', 1, SeatStatus.BOOKED))
    }
}