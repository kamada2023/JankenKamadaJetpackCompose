package com.example.jankenkamadajetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class FinalResultActivity : AppCompatActivity() {
    private val countWin:Int = CountApp().getWinCount()
    private val countLose:Int = CountApp().getLoseCount()
    private val countDraw:Int = CountApp().getDrawCount()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalResult()
        }
    }
    @Composable
    fun FinalResult() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            Text(
                text = stringResource(id = R.string.result),
                modifier = Modifier
                    .weight(2f, fill = true)
                    .align(Alignment.CenterHorizontally),
                fontSize = 30.sp
            )
            ResultImage(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.win_count, countWin),
                modifier = Modifier
                    .weight(1f, fill = true)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.win_count, countLose),
                modifier = Modifier
                    .weight(1f, fill = true)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.win_count, countDraw),
                modifier = Modifier
                    .weight(1f, fill = true)
                    .fillMaxWidth()
            )
            Button(
                onClick = { moveTitle() },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, false)
            ) {
                Text(text = stringResource(id = R.string.back_title))
            }
        }
    }
    @Composable
    fun ResultImage(modifier: Modifier){
        if (countWin > countLose){
            Image(painter = painterResource(id = R.drawable.youwin),
                contentDescription = null,
                modifier = modifier)
            CountApp().setNumOfWins()
        }else if (countLose > countWin){
            Image(painter = painterResource(id = R.drawable.youlose),
                contentDescription = null,
                modifier = modifier)
            CountApp().setNumOfLoses()
        }else{
            Image(painter = painterResource(id = R.drawable.drawgame),
                contentDescription = null,
                modifier = modifier)
            CountApp().setNumOfDraws()
        }

        if (CountApp().getBattleFormat()==1){
            if (countDraw > (CountApp().getCount()/2)){
                Image(painter = painterResource(id = R.drawable.drawgame),
                    contentDescription = null,
                    modifier = modifier)
                CountApp().setNumOfDraws()
            }
        }
    }
    private fun moveTitle(){
        val intent = Intent(application,TitleActivity::class.java)
        startActivity(intent)
    }
}