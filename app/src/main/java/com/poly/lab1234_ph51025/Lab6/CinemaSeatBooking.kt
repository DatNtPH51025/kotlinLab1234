package com.poly.lab1234_ph51025.Lab6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random


@Composable
fun CinemaSeatBookingScreen(seats: List<Seat>, totalSeatsPerRow: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Text(
            text = "Screen",
            modifier = Modifier.padding(16.dp),
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(totalSeatsPerRow),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(seats) { seat ->
                SeatComposable(seat = seat)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val textModifier = Modifier.padding(start = 4.dp, end = 16.dp)

            val legendSeats = listOf(
                Seat('X', 1, SeatStatus.EMPTY) to "Available",
                Seat('Y', 1, SeatStatus.SELECTED) to "Selected",
                Seat('Z', 1, SeatStatus.BOOKED) to "Booked"
            )

            legendSeats.forEach { (seat, label) ->
                SeatComposable(seat, clickable = false)
                Text(text = label, style = MaterialTheme.typography.titleSmall, modifier = textModifier)
            }
        }
    }
}

fun createTheaterSeating(totalRows: Int, totalSeatsPerRow: Int, aisleRow: Int, aisleColumn: Int): List<Seat> {
    val seats = mutableListOf<Seat>()

    for (rowIndex in 0 until totalRows) {
        for (seatIndex in 1..totalSeatsPerRow) {
            val isAisle = rowIndex == aisleRow || seatIndex == aisleColumn
            val status = when {
                isAisle -> SeatStatus.AISLE
                Random.nextInt(0, 100) % 2 == 0 -> SeatStatus.BOOKED
                else -> SeatStatus.EMPTY
            }

            seats.add(Seat(row = ('A' + rowIndex).coerceAtMost('Z'), number = seatIndex, status = status))
        }
    }
    return seats
}

// Thông số cho rạp chiếu phim
const val totalRows = 9
const val totalSeatsPerRow = 9
const val aisleRow = 4
const val aisleColumn = 5

@Preview
@Composable
fun PreviewCinemaSeatBooking() {
    CinemaSeatBookingScreen(
        seats = createTheaterSeating(totalRows, totalSeatsPerRow, aisleRow, aisleColumn),
        totalSeatsPerRow = totalSeatsPerRow
    )
}
