package com.example.jankenkamadajetpackcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

class HalfwayProgressActivity : AppCompatActivity() {
    private val countApp = CountApp.create()
    private val battleCount:Int = countApp.getAddCount()
    private val countWin:Int = countApp.getWinCount()
    private val countLose:Int = countApp.getLoseCount()
    private val countDraw:Int = countApp.getDrawCount()
    @SuppressLint("StringFormatMatches", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HalfwayProgress()
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    @Composable
    fun HalfwayProgress() {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.halfway_title),
                fontSize = 30.sp,
                modifier = Modifier
                    .weight(2f, fill = true)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.halfway_sub, battleCount),
                fontSize = 30.sp,
                modifier = Modifier
                    .weight(3f, fill = true)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.win_count, countWin),
                modifier = Modifier
                    .weight(1f, fill = true)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.lose_count, countLose),
                modifier = Modifier
                    .weight(1f, fill = true)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.draw_count, countDraw),
                modifier = Modifier
                    .weight(1f, fill = true)
                    .fillMaxWidth()
            )
            Button(onClick = { moveMain() }, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.next_battle))
            }
        }
    }
    private fun moveMain(){
        val intent = Intent(application,MainActivity::class.java)
        startActivity(intent)
    }

}