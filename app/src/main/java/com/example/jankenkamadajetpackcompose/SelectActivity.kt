package com.example.jankenkamadajetpackcompose

import android.annotation.SuppressLint
import android.content.Intent

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlin.math.roundToInt

class SelectActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "SourceLockedOrientationActivity", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Select()
        }
    }
    @Composable
    @Preview(showBackground = true)
    fun Select() {
        var mode by remember { mutableStateOf(0) }
        var count by remember { mutableStateOf(0) }

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
            Row(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(gameMode) {
                    top.linkTo(battleFormat.bottom)
                    bottom.linkTo(gameCount.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                if (mode == 0){
                    Text(text = stringResource(id = R.string.round_robin_battle),
                        fontSize = 40.sp)
                }else{
                    Text(text = stringResource(id = R.string.star_battle),
                        fontSize = 40.sp)
                }
                Slider(value = 0f, onValueChange = { mode++ },
                    enabled = true,
                    valueRange = 0f .. 1f,
                    steps = 1)
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(gameCount) {
                    top.linkTo(gameMode.bottom)
                    bottom.linkTo(gameCount.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(text = stringResource(id = R.string.round1,count),
                    fontSize = 27.sp)
                Slider(value = 0f, onValueChange = { _, -> count++ },
                    valueRange = 0f .. 10f,
                    enabled = true,
                    steps = 9)
            }
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
            Text(text =
            if (mode == 0) {
                stringResource(id = R.string.rule_robin)
            }else{
                stringResource(id = R.string.rule_star)
            },
                fontSize = 19.sp,
                modifier = Modifier.constrainAs(ruleExp) {
                    top.linkTo(rule.bottom)
                    bottom.linkTo(gameStart.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Button(onClick = { moveMain() },
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
    private fun moveMain(){
        val intent = Intent(application,MainActivity::class.java)
        startActivity(intent)
    }

    @Composable
    fun SeekBar() {
        var offsetX by remember { mutableStateOf(0f) }
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                    }
                )
                .size(30.dp)
                .background(Color.Blue, CircleShape)
        )
    }
}