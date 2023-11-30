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
    private val countApp = CountApp.create()
    private val countWin: Int = countApp.getWinCount()
    private val countLose: Int = countApp.getLoseCount()
    private val countDraw: Int = countApp.getDrawCount()

    enum class Result {
        WIN,
        LOSE,
        DRAW
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var finResult: Int
        finResult = if (countWin > countLose) {
            countApp.setNumOfWins()
            Result.WIN.ordinal
        } else if (countLose > countWin) {
            countApp.setNumOfLoses()
            Result.LOSE.ordinal
        } else {
            countApp.setNumOfDraws()
            Result.DRAW.ordinal
        }

        if (countApp.getBattleFormat() == 1) {
            if (countDraw > (countApp.getCount() / 2)) {
                countApp.setNumOfDraws()
                finResult = Result.DRAW.ordinal
            }
        }

        setContent {
            FinalResult(finResult)
        }
    }

    @Composable
    fun FinalResult(finResult: Int) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.result),
                modifier = Modifier
                    .weight(1f, fill = true)
                    .align(Alignment.CenterHorizontally),
                fontSize = 30.sp
            )

            ResultImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, fill = true)
                    .fillMaxWidth(),
                result = finResult
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
    fun ResultImage(modifier: Modifier, result: Int) {
        when (result) {
            Result.WIN.ordinal -> {
                Image(
                    painter = painterResource(id = R.drawable.youwin),
                    contentDescription = null,
                    modifier = modifier
                )
            }

            Result.LOSE.ordinal -> {
                Image(
                    painter = painterResource(id = R.drawable.youlose),
                    contentDescription = null,
                    modifier = modifier
                )
            }

            Result.DRAW.ordinal -> {
                Image(
                    painter = painterResource(id = R.drawable.drawgame),
                    contentDescription = null,
                    modifier = modifier
                )
            }
        }
    }

    private fun moveTitle() {
        countApp.clearResult()
        val intent = Intent(application, TitleActivity::class.java)
        startActivity(intent)
    }
    override fun onBackPressed() {
    }
}