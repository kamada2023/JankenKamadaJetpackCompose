package com.example.jankenkamadajetpackcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class ResultActivity : AppCompatActivity() {

    private val countApp = CountApp.create()
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId: Int = intent.getIntExtra("hand",0)
        countApp.setAddCount()
        val seed : Long = System.currentTimeMillis()
        val cpu :Int = Random(seed).nextInt(3)
        if (userId==cpu){
            if (countApp.getAddCount() <= countApp.getCount()){
                countApp.setDrawCount()
            }
        }else if ((userId==2 && cpu==0) || ((userId+1)==cpu)){
            if (countApp.getAddCount() <= countApp.getCount()){
                countApp.setWinCount()
            }
        }else{
            if (countApp.getAddCount() <= countApp.getCount()){
                countApp.setLoseCount()
            }
        }
        setContent {
            ResultAct(user = userId, cpu = cpu)
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    @Composable
    fun ResultAct(user: Int,cpu: Int) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResultImage(modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f, fill = true),
                user = user,
                cpu = cpu)
            Box(
                modifier = Modifier
                    .weight(0.1f, fill = true)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.cpu))
                HandImage(id = cpu)
            }
            ResultText(modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
                user = user, cpu = cpu, fontSize = 30)

            Box(
                modifier = Modifier
                    .weight(0.1f, fill = true)
                    .fillMaxWidth()
            )
            {
                Text(text = stringResource(R.string.user))
                HandImage(id = user)
            }

            Button(
                onClick = { continueOrEnd() },
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(text = if (countApp.getCount() == countApp.getAddCount()) {
                    stringResource(id = R.string.next_result)
                }else if (countApp.getAddCount() == 1) {
                    stringResource(id = R.string.next_battle)
                }else {
                    stringResource(id = R.string.next_scene)
                })
            }
        }
    }

    @Composable
    fun HandImage(id: Int){
        when (id) {
            0 -> Image(
                painter = painterResource(id = R.drawable.j_gu02),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            1 -> Image(
                painter = painterResource(id = R.drawable.j_ch02),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            2 -> Image(
                painter = painterResource(id = R.drawable.j_pa02),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
    @Composable
    fun ResultImage(modifier: Modifier, user: Int,cpu: Int) {
        if (user==cpu){
            Image(painter = painterResource(id = R.drawable.draw),
                contentDescription = null,
                modifier = modifier)
        }else if ((user==2 && cpu==0) || ((user+1)==cpu)){
            Image(painter = painterResource(id = R.drawable.win),
                contentDescription = null,
                modifier = modifier)
        }else{
            Image(painter = painterResource(id = R.drawable.lose),
                contentDescription = null,
                modifier = modifier)
        }
    }

    @Composable
    fun ResultText(modifier: Modifier, user: Int,cpu: Int,fontSize: Int) {
        if (user==cpu){
            Text(text = stringResource(id = R.string.draw),
                fontSize = fontSize.sp,
                modifier = modifier)
        }else if ((user==2 && cpu==0) || ((user+1)==cpu)){
            Text(text = stringResource(id = R.string.win),
                fontSize = fontSize.sp,
                modifier = modifier)
        }else{
            Text(text = stringResource(id = R.string.lose),
                fontSize = fontSize.sp,
                modifier = modifier)
        }
    }

    private fun continueOrEnd(){
        var conOrEnd:Intent
        val first = Intent(application,MainActivity::class.java)
        val end = Intent(application,FinalResultActivity::class.java)
        val con = Intent(application,HalfwayProgressActivity::class.java)
        val game:Int = countApp.getCount()
        val rounds:Int = countApp.getAddCount()
        val win:Int = countApp.getWinCount()
        val lose:Int = countApp.getLoseCount()

        conOrEnd = if (rounds==1){ first }
        else{ con }

        if (countApp.getBattleFormat() == 1){
            if ((win-lose) > (game-rounds)){
                conOrEnd = end
            }else if ((lose-win) > (game-rounds)){
                conOrEnd = end
            }else if (countApp.getDrawCount() > (game/2)){
                conOrEnd = end
            }
        }

        if (game==rounds){
            conOrEnd = end
        }else if (game < rounds){
            conOrEnd = end
        }

        startActivity(conOrEnd)
    }
}