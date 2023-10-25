package com.example.jankenkamadajetpackcompose

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TableLayout
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jankenkamadajetpackcompose.ui.theme.JankenKamadaKotlin2Theme

class TitleActivity : AppCompatActivity() {
    private val countApp = CountApp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "screen1"){
                composable(route = "screen1") {
                    Title(onClickButton = { navController.navigate("screen2") })
                }
                composable(route = "screen2") {
                    Select(onClickButton = { navController.navigateUp() })
                }
            }
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Composable
    fun Title(onClickButton: ()->Unit = {}){
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        val numofWin = remember {
//            mutableStateOf("")
//        }
//        val numOfLose = remember {
//            mutableStateOf("")
//        }
//        val numOfDraw = remember {
//            mutableStateOf("")
//        }
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (button,title,topImage,
                speech,result,reset
            ) = createRefs()
            Image(painter = painterResource(id = R.drawable.title), contentDescription = null,
                modifier = Modifier.constrainAs(title){
                    top.linkTo(parent.top)
                    bottom.linkTo(topImage.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Image(painter = painterResource(id = R.drawable.main), contentDescription = null,
                modifier = Modifier.constrainAs(topImage){
                    top.linkTo(title.bottom)
                    bottom.linkTo(speech.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(stringResource(R.string.chara_speech),
                fontSize = 40.sp,
                modifier = Modifier.constrainAs(speech){
                top.linkTo(topImage.bottom)
                bottom.linkTo(result.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                })
            Text(stringResource(R.string.total_result,countApp.getNumOfWins(),
                countApp.getNumOfLoses(),countApp.getNumOfDraws()), fontSize = 27.sp,
                modifier = Modifier.constrainAs(result){
                    top.linkTo(speech.bottom)
                    bottom.linkTo(reset.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Button(
                onClick = { resetAct() },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .constrainAs(reset) {
                        top.linkTo(result.bottom)
                        bottom.linkTo(button.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ){
                Text(text = stringResource(R.string.reset), fontSize = 36.sp)
            }

            Button(
                onClick = onClickButton,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(button) {
                        top.linkTo(reset.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ){
                Text(text = stringResource(R.string.next_scene), fontSize = 36.sp)

            }

        }
    }

    private fun resetAct(){
        countApp.clearTotalResult()

    }
    @Composable
    fun Select(onClickButton: ()->Unit = {}){
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (battleFormat,gameMode,modeChange, gameCount,
                roundCount,rule,ruleExp,gameStart) = createRefs()
            Text(text = stringResource(id = R.string.battle_format), fontSize = 30.sp,
                modifier = Modifier.constrainAs(battleFormat){
                    top.linkTo(parent.top)
                    bottom.linkTo(gameMode.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = stringResource(id = R.string.round_robin_battle),
                fontSize = 40.sp,
                modifier = Modifier.constrainAs(gameMode){
                    top.linkTo(battleFormat.bottom)
                    bottom.linkTo(gameCount.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            SeekBar()
            Text(text = stringResource(id = R.string.round1))
            SeekBar()
            Text(text = stringResource(id = R.string.explanation_rule),
            modifier = Modifier.constrainAs(rule){
                top.linkTo(gameMode.bottom)
                bottom.linkTo(ruleExp.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            Text(text = stringResource(id = R.string.rule1),
            modifier = Modifier.constrainAs(ruleExp) {
                top.linkTo(rule.bottom)
                bottom.linkTo(gameStart.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            Button(onClick = onClickButton,
            modifier = Modifier.constrainAs(gameStart){
                top.linkTo(rule.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                Text(text = stringResource(id = R.string.game_start))
            }

        }
    }
    @Composable
    private fun paint(): Paint {
        return Paint().apply {
            color = MaterialTheme.colorScheme.primary
            isAntiAlias = true
        }
    }
    @Composable
    fun SeekBar(
        @FloatRange(from = 0.0, to = 1.0) progress: Float,
        fixedWidth: Dp? = null,
        onChangeProgress: (Float) -> Unit
    ) {
        val squareSize = 32.dp
        val barHeight = 8.dp
        val fixedWidthPx = withDensity(ambientDensity()) { fixedWidth?.toPx()?.value }
        val (width, setWidth) = state {
            fixedWidthPx ?: 0f
        }
        if (width == 0f) {
            Container(
                modifier = LayoutWidth.Fill
            ) {
                Draw { _, parentSize ->
                    val newWidth = parentSize.width.value
                    if (newWidth != width) {
                        setWidth(newWidth)
                    }
                }
            }
        } else {
            val squareSizePx = withDensity(ambientDensity()) { squareSize.toPx().value }

            val max = width - squareSizePx
            val min = 0.dp
            val (minPx, maxPx) = withDensity(ambientDensity()) {
                min.toPx().value to max
            }
            val position = animatedDragValue(maxPx * progress, minPx, maxPx)
            val paint = paint()

            Draggable(
                dragDirection = DragDirection.Horizontal,
                dragValue = position,
                onDragValueChangeRequested = {
                    position.animatedFloat.snapTo(it)
                    onChangeProgress(position.value / max)
                }
            ) {
                Container(
                    modifier = fixedWidth?.let { LayoutWidth(it) } ?: LayoutWidth.Fill,
                    alignment = Alignment.CenterLeft,
                    height = squareSize
                ) {
                    Stack {
                        Padding(
                            top = squareSize / 2 - barHeight / 2,
                            left = squareSize / 2,
                            right = squareSize / 2
                        ) {
                            ColoredRect(
                                Color.LightGray,
                                height = barHeight
                            )
                        }
                        Draw { canvas, _ ->
                            canvas.drawCircle(
                                Offset(position.value + squareSizePx / 2, squareSizePx / 2),
                                squareSizePx / 2,
                                paint
                            )
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TitlePreview() {
        JankenKamadaKotlin2Theme {
            Title()
            Select()
        }
    }
}