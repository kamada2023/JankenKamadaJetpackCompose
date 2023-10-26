package com.example.jankenkamadajetpackcompose

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jankenkamadajetpackcompose.ui.theme.JankenKamadaKotlin2Theme


class TitleActivity : AppCompatActivity() {
    private val countApp = CountApp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "screen1") {
                //対象のアイテム
                composable(route = "screen1") {
                    Title(onClickButton = { navController.navigate("screen2") })
                }
                composable(route = "screen2") {
                    Select(onClickButton = { navController.navigate("screen3") })
                }
                composable(route = "screen3")
                {
                    Main { id ->
                        navController.navigate("screen4/$id")
                    }
                }
                composable(route = "screen4/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("id") ?: 0
                    ResultAct(id) { navController.navigate("screen5") }
                }
                composable(route = "screen5") {
                    HalfwayProgress(onClickButton = { navController.navigate("screen6") })
                }
                composable(route = "screen6") {
                    FinalResult(onClickButton = { navController.navigate("screen1") })
                }
            }
        }
    }

    @Composable
    fun Title(onClickButton: () -> Unit = {}) {
        var counter by remember {
            mutableStateOf(0)
        }
        val numOfWin = remember(counter) {
            countApp.clearTotalResult()
            mutableStateOf(countApp.getNumOfWins())
        }
        val numOfLose = remember(counter) {
            countApp.clearTotalResult()
            mutableStateOf(countApp.getNumOfLoses())
        }
        val numOfDraw = remember(counter) {
            mutableStateOf(countApp.getNumOfDraws())
        }
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (button, title, topImage,
                speech, result, reset
            ) = createRefs()
            Image(painter = painterResource(id = R.drawable.title), contentDescription = null,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    bottom.linkTo(topImage.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Image(painter = painterResource(id = R.drawable.main), contentDescription = null,
                modifier = Modifier.constrainAs(topImage) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(speech.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(stringResource(R.string.chara_speech),
                fontSize = 40.sp,
                modifier = Modifier.constrainAs(speech) {
                    top.linkTo(topImage.bottom)
                    bottom.linkTo(result.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(stringResource(
                R.string.total_result, numOfWin,
                numOfLose, numOfDraw
            ), fontSize = 27.sp,
                modifier = Modifier.constrainAs(result) {
                    top.linkTo(speech.bottom)
                    bottom.linkTo(reset.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Button(
                onClick = { counter++ },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .constrainAs(reset) {
                        top.linkTo(result.bottom)
                        bottom.linkTo(button.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
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
            ) {
                Text(text = stringResource(R.string.next_scene), fontSize = 36.sp)

            }

        }
    }

    @Composable
    fun Select(onClickButton: () -> Unit = {}) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (battleFormat, gameMode, gameCount,
                rule, ruleExp, gameStart) = createRefs()
            Text(text = stringResource(id = R.string.battle_format), fontSize = 30.sp,
                modifier = Modifier.constrainAs(battleFormat) {
                    top.linkTo(parent.top)
                    bottom.linkTo(gameMode.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = stringResource(id = R.string.round_robin_battle),
                fontSize = 40.sp,
                modifier = Modifier.constrainAs(gameMode) {
                    top.linkTo(battleFormat.bottom)
                    bottom.linkTo(gameCount.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            //SeekBar()
            Text(text = stringResource(id = R.string.round1),
                fontSize = 27.sp,
                modifier = Modifier.constrainAs(gameCount) {
                    top.linkTo(gameMode.bottom)
                    bottom.linkTo(gameCount.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            //SeekBar()
            Text(text = stringResource(id = R.string.explanation_rule),
                fontSize = 19.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(rule) {
                        top.linkTo(gameCount.bottom)
                        bottom.linkTo(ruleExp.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            Text(text = stringResource(id = R.string.rule1),
                fontSize = 19.sp,
                modifier = Modifier.constrainAs(ruleExp) {
                    top.linkTo(rule.bottom)
                    bottom.linkTo(gameStart.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Button(onClick = onClickButton,
                modifier = Modifier.constrainAs(gameStart) {
                    top.linkTo(rule.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(text = stringResource(id = R.string.game_start), fontSize = 36.sp)
            }
        }
    }

    enum class my_hand(val myHand: Int) {
        gu(0),
        ch(1),
        pa(2)
    }

    @Composable
    fun Main(onClickButton: (Int) -> Unit = { }) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (battle_shout, draw_main,
                janken,
                subtitle) = createRefs()
            Text(text = stringResource(id = R.string.title), fontSize = 20.sp,
                modifier = Modifier.constrainAs(battle_shout) {
                    top.linkTo(parent.top)
                    bottom.linkTo(draw_main.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Image(painter = painterResource(id = R.drawable.main), contentDescription = null,
                modifier = Modifier.constrainAs(draw_main) {
                    top.linkTo(battle_shout.bottom)
                    bottom.linkTo(janken.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(janken) {
                        top.linkTo(draw_main.bottom)
                        bottom.linkTo(subtitle.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.j_gu02), contentDescription = null,
                    Modifier.clickable { onClickButton((my_hand.gu).myHand) })
                Image(painter = painterResource(id = R.drawable.j_ch02), contentDescription = null,
                    Modifier.clickable { onClickButton((my_hand.ch).myHand) })
                Image(painter = painterResource(id = R.drawable.j_pa02), contentDescription = null,
                    Modifier.clickable { onClickButton((my_hand.pa).myHand) })
            }
            Text(text = stringResource(id = R.string.subtitle),
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .constrainAs(subtitle) {
                        top.linkTo(janken.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
        }
    }

    @Composable
    fun ResultAct(id: Int, onClickButton: () -> Unit = {}) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (result_draw, cpu,
                result, user, next_battle) = createRefs()
            Image(painter = painterResource(id = R.drawable.draw), contentDescription = null,
                modifier = Modifier.constrainAs(result_draw) {
                    top.linkTo(parent.top)
                    bottom.linkTo(cpu.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Box(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(cpu) {
                    top.linkTo(result_draw.bottom)
                    bottom.linkTo(result.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(text = stringResource(R.string.cpu))
                Image(painter = painterResource(id = R.drawable.j_gu02), contentDescription = null)
            }
            Text(text = stringResource(id = R.string.draw), fontSize = 30.sp,
                modifier = Modifier.constrainAs(result_draw) {
                    top.linkTo(cpu.bottom)
                    bottom.linkTo(user.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Box(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(user) {
                    top.linkTo(result_draw.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(text = stringResource(R.string.user))
                if (id == 0) {
                    Image(
                        painter = painterResource(id = R.drawable.j_gu02),
                        contentDescription = null
                    )
                } else if (id == 1) {
                    Image(
                        painter = painterResource(id = R.drawable.j_ch02),
                        contentDescription = null
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.j_pa02),
                        contentDescription = null
                    )
                }
            }
        }
    }

    @Composable
    fun HalfwayProgress(onClickButton: () -> Unit = {}) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (test) = createRefs()
        }
    }

    @Composable
    fun FinalResult(onClickButton: () -> Unit = {}) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (test) = createRefs()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TitlePreview() {
        JankenKamadaKotlin2Theme {
            Main()
        }
    }
}