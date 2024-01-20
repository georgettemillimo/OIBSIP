package com.devmillimo.calculatorapp

import android.os.Bundle
import android.text.TextUtils.split
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
import androidx.compose.runtime.*
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
                    color = MaterialTheme.colors.secondary
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

                            CalculatorButton(
                                icon = Icons.Outlined.Refresh,
                                type = CalculatorButtonType.Reset),
                            CalculatorButton("0", CalculatorButtonType.Normal),
                            CalculatorButton(".", CalculatorButtonType.Normal),
                            CalculatorButton("=", CalculatorButtonType.Action),

                        )
                    }
                    val (uiText, setuiText) = remember {
                        mutableStateOf("0")
                    }

                    LaunchedEffect(uiText){
                        if (uiText.startsWith("0") && uiText != "0"){
                            setuiText(uiText.substring(1))
                        }
                    }
                    val (input, setInput) = remember {
                        mutableStateOf<String?>(null)
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
                                tint = Color.Black
                            )

                            Icon(modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.icon_darkmode), contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                    //==============================Working Area========================

                   Box(modifier = Modifier
                       .fillMaxSize(),
                       contentAlignment = Alignment.BottomCenter
                   ){

                    Column() {
                        Text(modifier = Modifier.padding(12.dp), text = uiText, fontSize = 48.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))

  //========================================LAZY VERTICAL GRID FOR BUTTONS======================================================

                        LazyVerticalGrid(
                            modifier = Modifier.padding(8.dp)
                                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                                .background(MaterialTheme.colors.primary),
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

         // =======================================Normal Button============================================================================
                                            CalculatorButtonType.Normal->{
                                                runCatching {
                                                    setuiText(uiText.toInt().toString()+it.text)
                                                }.onFailure {exception: Throwable ->
                                                    setuiText(uiText+it.text)
                                                }
                                                setInput((input ?: "") + it.text)
                                                if (viewModel.action.value.isNotEmpty()){
                                                    if (viewModel.secondNumber.value == null){
                                                        viewModel.setsecondNumber(it.text!!.toDouble())
                                                    }else{
                                                        if (viewModel.secondNumber.value.toString().split(".")[1]=="0"){
                                                            viewModel.setsecondNumber((viewModel.secondNumber.value.toString()
                                                                .split(".").first() + it.text!!).toDouble())
                                                        }else{
                                                            viewModel.setsecondNumber((viewModel.secondNumber.value.toString() + it.text!!).toDouble())
                                                        }

                                                    }
                                                }
                                            }

          // =======================================Action Button============================================================================
                                            CalculatorButtonType.Action->{

                                                if (it.text == "="){
                                                    val result = viewModel.getResult()
                                                    setuiText(result.toString())
                                                    setInput(null)
                                                    viewModel.resetAll()

                                                } else{
                                                    runCatching {
                                                        setuiText(uiText.toInt().toString()+it.text)
                                                    }.onFailure {exception: Throwable ->
                                                        setuiText(uiText+it.text)
                                                    }


                                                    if (input != null){
                                                        if (viewModel.firstNumber.value == null){
                                                            viewModel.setFirstNumber(input.toDouble())
                                                        }
                                                        else{
                                                            viewModel.setsecondNumber(input.toDouble())
                                                        }
                                                        viewModel.setAction(it.text!!)
                                                        setInput(null)
                                                    }
                                                }

                                            }
            // =======================================rest Button============================================================================
                                            CalculatorButtonType.Reset->{
                                                setuiText("0")
                                                setInput(null)
                                                viewModel.resetAll()
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

}


data class CalculatorButton(
     val text:String? = null,
     val type: CalculatorButtonType,
     val icon: ImageVector? = null
)
enum class CalculatorButtonType{
    Normal, Action, Reset
}