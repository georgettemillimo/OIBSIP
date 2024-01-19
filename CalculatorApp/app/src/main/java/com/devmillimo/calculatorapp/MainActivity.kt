package com.devmillimo.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.devmillimo.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {

                    val calculatorButtons = remember {
                        mutableStateListOf(
                            CalculatorButton( "AC", CalculatorButtonType.Reset),
                            CalculatorButton("÷", CalculatorButtonType.Action),
                            CalculatorButton( "AC", CalculatorButtonType.Reset),
                            CalculatorButton("+", CalculatorButtonType.Action),

                            CalculatorButton( "7", CalculatorButtonType.Normal),
                            CalculatorButton("8", CalculatorButtonType.Normal),
                            CalculatorButton( "9", CalculatorButtonType.Normal),
                            CalculatorButton("x", CalculatorButtonType.Action),

                            CalculatorButton( "4", CalculatorButtonType.Normal),
                            CalculatorButton("5", CalculatorButtonType.Normal),
                            CalculatorButton( "6", CalculatorButtonType.Normal),
                            CalculatorButton("-", CalculatorButtonType.Action),

                            CalculatorButton( "1", CalculatorButtonType.Normal),
                            CalculatorButton("2", CalculatorButtonType.Normal),
                            CalculatorButton( "3", CalculatorButtonType.Normal),
                            CalculatorButton("+", CalculatorButtonType.Action),

                            CalculatorButton(icon = Icons.Outlined.Refresh, type = CalculatorButtonType.Normal),
                            CalculatorButton("0", CalculatorButtonType.Normal),
                            CalculatorButton(".", CalculatorButtonType.Normal),
                            CalculatorButton("=", CalculatorButtonType.Action),

                        )
                    }

                    //BOX TO DESIGN THE BUTTONS OF THE CALULATOR==============================================================
                   Box(modifier = Modifier
                       .fillMaxSize()
                       .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                       .background(MaterialTheme.colors.secondary),
                       contentAlignment = Alignment.BottomCenter ){

                       LazyVerticalGrid(columns = GridCells.Fixed(4)){

                           items(calculatorButtons){
                               Box(modifier = Modifier
                                   .clip(RoundedCornerShape(4.dp))
                                   .background(MaterialTheme.colors.primary),
                                   contentAlignment = Alignment.Center
                               ){
                                    if (it.text != null){
                                        Text(it.text)
                                    }else{
                                        Icon(imageVector = it.icon!!, contentDescription = null)
                                    }
                               }
                           }

                       }

                   }

                }
            }
        }
    }
}


data class CalculatorButton(
     val text:String? = null,
     val type: CalculatorButtonType,
     val icon: ImageVector? = null
)
enum class CalculatorButtonType{
    Normal, Action, Reset
}