package com.example.jankenkamadajetpackcompose

import android.annotation.SuppressLint
import android.content.Intent

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

class SelectActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "SourceLockedOrientationActivity", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Select()
        }
    }
    @Composable
    fun Select() {
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
}