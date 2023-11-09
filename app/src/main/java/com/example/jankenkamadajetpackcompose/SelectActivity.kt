package com.example.jankenkamadajetpackcompose

import android.content.Intent

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

class SelectActivity : AppCompatActivity() {
    private val countApp = CountApp.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Select()
        }
    }

    @Composable
    fun Select() {
        var mode by remember { mutableStateOf(0) }
        var count by remember { mutableStateOf(1) }
        var ruleText: String

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (
                battleFormat,
                gameMode,
                gameCount,
                rule,
                ruleExp,
                gameStart
            ) = createRefs()

            Text(
                text = stringResource(id = R.string.battle_format),
                fontSize = 30.sp,
                modifier = Modifier.constrainAs(battleFormat) {
                    top.linkTo(parent.top)
                    bottom.linkTo(gameMode.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(gameMode) {
                        top.linkTo(battleFormat.bottom)
                        bottom.linkTo(gameCount.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {

                if (mode == 0) Text(
                    text = stringResource(id = R.string.round_robin_battle),
                    fontSize = 40.sp
                )
                else Text(text = stringResource(id = R.string.star_battle), fontSize = 40.sp)

                Slider(
                    value = mode.toFloat(), onValueChange = { mode = it.toInt() },
                    enabled = true,
                    valueRange = 0f..1f,
                    steps = 1
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(gameCount) {
                        top.linkTo(gameMode.bottom)
                        bottom.linkTo(gameCount.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Slider(
                    value = count.toFloat(), onValueChange = { count = it.toInt() },
                    valueRange = 1f..10f,
                    enabled = true,
                    steps = 9,
                    modifier = Modifier.weight(3.3f, false)
                )

                Text(
                    text = stringResource(id = R.string.round1, count),
                    fontSize = 27.sp,
                    modifier = Modifier.weight(1.7f, false),
                )
            }

            Text(
                text = stringResource(id = R.string.explanation_rule),
                fontSize = 19.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(rule) {
                        top.linkTo(gameCount.bottom)
                        bottom.linkTo(ruleExp.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            ruleText = if (mode == 0) stringResource(id = R.string.rule_robin)
            else stringResource(id = R.string.rule_star)

            Text(
                text = ruleText,
                fontSize = 19.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(ruleExp) {
                        top.linkTo(rule.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Button(
                onClick = {
                    countApp.setCount(count)
                    countApp.setBattleFormat(mode)
                    moveMain()
                },
                modifier = Modifier.constrainAs(gameStart) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Text(text = stringResource(id = R.string.game_start), fontSize = 36.sp)
            }
        }
    }

    private fun moveMain() {
        val intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
    }
}