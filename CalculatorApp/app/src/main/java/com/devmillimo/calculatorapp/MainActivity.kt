package com.devmillimo.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devmillimo.calculatorapp.ui.theme.CalculatorAppTheme
import com.devmillimo.calculatorapp.ui.theme.Cyan
import com.devmillimo.calculatorapp.ui.theme.Red

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

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
                            CalculatorButton( "AC", CalculatorButtonType.Reset),
                            CalculatorButton( "AC", CalculatorButtonType.Reset),
                            CalculatorButton("÷", CalculatorButtonType.Action),

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
                    val input = remember {
                        mutableStateOf<Double?>(null)
                    }
                   Box(modifier = Modifier
                       .fillMaxSize(),
                       contentAlignment = Alignment.BottomCenter
                   ){

                       LazyVerticalGrid(
                           modifier = Modifier
                               .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                               .background(MaterialTheme.colors.primary)
                               .padding(8.dp),
                           columns = GridCells.Fixed(4),
                           verticalArrangement = Arrangement.spacedBy(16.dp),
                           horizontalArrangement = Arrangement.spacedBy(16.dp),
                           contentPadding = PaddingValues(16.dp)

                       ){

                           items(calculatorButtons){
                             CalcButton(
                                 button = it,
                                 onClick = {
                                     when(it.type){
                                         CalculatorButtonType.Normal->{

                                         }
                                         CalculatorButtonType.Action->{
                                                viewModel.setAction(it.text!!)
                                         }
                                         CalculatorButtonType.Reset->{

                                         }
                                     }
                                 }
                                 )
                           }

                       }

                   }

                }
            }
        }
    }
}

@Composable
fun CalcButton( button: CalculatorButton, onClick:() -> Unit){
    Box(modifier = Modifier
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colors.secondary)
        .fillMaxWidth()
        .aspectRatio(1f)
        .clickable {
            onClick()
        },
        contentAlignment = Alignment.Center
    ){
        val contentColor =
            if (button.type == CalculatorButtonType.Normal)
                Color.Black
            else if (button.type == CalculatorButtonType.Action)
                Red
            else
                Cyan

        if (button.text != null){
            Text(
                button.text,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                fontSize = if (button.type == CalculatorButtonType.Action)25.sp else 20.sp
            )
        }else{
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = button.icon!!,
                contentDescription = null,
                tint = contentColor)
        }
    }

    //==============================Working Area========================
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
        ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clip(
                    RoundedCornerShape(8.dp)
                )
        ) {
            Icon(modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.icon_nightmode), contentDescription = null,
                tint = Color.White
            )

            Icon(modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.icon_darkmode), contentDescription = null,
                tint = Color.White
            )
        }
    }
    //==============================Working Area========================

}


data class CalculatorButton(
     val text:String? = null,
     val type: CalculatorButtonType,
     val icon: ImageVector? = null
)
enum class CalculatorButtonType{
    Normal, Action, Reset
}