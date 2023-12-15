package com.belajar.capstoneapp.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.belajar.capstoneapp.PieChart
import com.belajar.capstoneapp.R
import com.belajar.capstoneapp.ui.component.DiaryFood
import com.belajar.capstoneapp.ui.component.SectionText
import com.belajar.capstoneapp.ui.theme.Dark100
import com.belajar.capstoneapp.ui.theme.Dark200
import com.belajar.capstoneapp.ui.theme.Green100
import com.belajar.capstoneapp.ui.theme.Green300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            ProfileCard()

            SectionText(stringResource(R.string.section_goals))
            Banner(
                navigateToDetail = navigateToDetail
            )
            // Preview with sample data
            PieChart(
                data = mapOf(
                    Pair("Carb", 150),
                    Pair("Protein", 120),
                    Pair("Less", 100),
                    Pair("Fat", 110)
                )
            )
            SectionText(stringResource(R.string.section_diary))
            LazyRow(
                modifier = modifier
                    .clickable { navigateToDetail("1") }
            ) {
                items(10) { // Replace 10 with the actual number of items in your DiaryFood
                    // DiaryFood item content goes here
                     DiaryFood(navigateToDetail = navigateToDetail)
                }
            }
        }
    }

    @Composable
    fun ProfileCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )

            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        Dark200
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Selamat Siang",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Fahriza",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

    @Composable
    fun Banner(
        modifier: Modifier = Modifier,
        navigateToDetail: (String) -> Unit,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Green100)) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.clickable {
                    navigateToDetail("1")
                }
            ) {
                Column (
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/512/485/485256.png",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                            .size(80.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
                Column() {
                    Text(
                        fontSize = 12.sp,
                        text = "Calory intake",
                        color = Dark100
                    )
                    Text(
                        text = "2000 cal",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Green300
                    )
                }
            }
        }
    }