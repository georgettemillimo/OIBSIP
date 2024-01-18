package com.devmillimo.unitconverter.ui.theme.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devmillimo.unitconverter.R


@Composable
fun MainScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ){

        Text(modifier = Modifier.fillMaxWidth(),
            text = "Unit Converter",
            fontFamily = FontFamily.Cursive,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold

            )

        Box(contentAlignment = Alignment.Center){

            Column() {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End) {
                        ConversionRow(
                            modifier = Modifier.fillMaxSize(),
                            conversionCode = "Kgs",
                            conversionName ="Kilograms",
                            onDropDownIconClicked = {}
                        )
                        Text(
                            text = "1000",
                            fontSize = 30.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End) {

                        Text(
                            text = "1000",
                            fontSize = 30.sp
                        )

                        ConversionRow(
                            modifier = Modifier.fillMaxSize(),
                            conversionCode = "Kgs",
                            conversionName ="Kilograms",
                            onDropDownIconClicked = {}
                        )

                    }
                }
            }

            Box(modifier = Modifier
                .padding(start = 40.dp)
                .clip(CircleShape)
                .clickable { }
                .background(color = Color.DarkGray)
            ){
                Icon(
                    painter = painterResource(R.drawable.baseline_sync),
                    contentDescription = "Swap Currency",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(25.dp),
                    tint = MaterialTheme.colors.onSurface)

            }
        }

    }

}


@Composable
fun ConversionRow(
    modifier: Modifier = Modifier,
    conversionCode: String,
    conversionName: String,
    onDropDownIconClicked:() -> Unit){

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = conversionCode, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        IconButton(onClick = onDropDownIconClicked) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open Bottom Sheet")
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(text = conversionName, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }

}
