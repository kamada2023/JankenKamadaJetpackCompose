package com.example.jankenkamadajetpackcompose

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


class TitleActivity : AppCompatActivity() {
    private val countApp = CountApp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        setContent {
            Title()
        }
    }

    @Composable
    @Preview(showBackground = true)
    fun Title() {
        //初回コンポーズ時実行
        val numOfWin by remember { mutableStateOf(countApp.getNumOfWins())}
        val numOfLose by remember { mutableStateOf(countApp.getNumOfWins())}
        val numOfDraw by remember { mutableStateOf(countApp.getNumOfWins())}

        val clear = remember(numOfWin,numOfLose,numOfDraw) {
            countApp.clearTotalResult()
            mutableStateOf(countApp.getNumOfWins())
            mutableStateOf(countApp.getNumOfLoses())
            mutableStateOf(countApp.getNumOfDraws())
        }

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (button, title, topImage,
                speech, result, reset
            ) = createRefs()
            Image(painter = painterResource(id = R.drawable.title),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(topImage.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            Image(painter = painterResource(id = R.drawable.main), contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topImage) {
                        top.linkTo(title.bottom)
                        bottom.linkTo(speech.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            Text(text = stringResource(R.string.chara_speech),
                fontSize = 40.sp,
                modifier = Modifier.constrainAs(speech) {
                    top.linkTo(topImage.bottom)
                    bottom.linkTo(result.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = stringResource(R.string.total_result, numOfWin, numOfLose, numOfDraw
            ), fontSize = 27.sp,
                modifier = Modifier.constrainAs(result) {
                    top.linkTo(speech.bottom)
                    bottom.linkTo(reset.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Button(
                onClick = { clear++ },
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
                onClick = { moveSelect() },
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
    private fun moveSelect(){
        val intent = Intent(application,SelectActivity::class.java)
        startActivity(intent)
    }

}