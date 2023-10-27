package com.example.jankenkamadajetpackcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables", "SourceLockedOrientationActivity", "StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }

    }
    enum class MyHand(val myHand: Int) {
        GU(0),
        CH(1),
        PA(2)
    }

    @Preview(showBackground = true)
    @Composable
    fun Main() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (battle_shout, draw_main,
                janken,
                subtitle) = createRefs()
            BattleShout(fontSize = 20.sp,
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
                    Modifier
                        .clickable { clickGu() }
                        .weight(1f))
                Image(painter = painterResource(id = R.drawable.j_ch02), contentDescription = null,
                    Modifier
                        .clickable { clickCh() }
                        .weight(1f))
                Image(painter = painterResource(id = R.drawable.j_pa02), contentDescription = null,
                    Modifier
                        .clickable { clickPa() }
                        .weight(1f))
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
    private fun clickGu(){
        intent.putExtra("hand",MyHand.GU)
        startActivity(Intent(application,ResultActivity::class.java))
    }
    private fun clickCh(){
        intent.putExtra("hand",MyHand.CH)
        startActivity(Intent(application,ResultActivity::class.java))
    }
    private fun clickPa(){
        intent.putExtra("hand",MyHand.PA)
        startActivity(Intent(application,ResultActivity::class.java))
    }
    @Composable
    fun BattleShout(modifier: Modifier,fontSize: TextUnit){
        val count = CountApp().getAddCount()
        if (count == 0) {
            Text(text = stringResource(id = R.string.title),
                fontSize = fontSize,
                modifier = modifier)
        }else{
            Text(text = stringResource(id = R.string.rounds,count+1),
                fontSize = fontSize,
                modifier = modifier)
        }
    }
}