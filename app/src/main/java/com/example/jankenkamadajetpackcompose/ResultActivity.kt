package com.example.jankenkamadajetpackcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ResultActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//
////cpu
//        val seed : Long = System.currentTimeMillis()
//        val cpu :Int = Random(seed).nextInt(3)
////user
//        val user: Int = intent.getIntExtra("hand",0)
//        CountApp().setAddCount()
//
//        if (user==cpu){
//            if (CountApp().getAddCount() <= CountApp().getCount()){
//                CountApp().setDrawCount()
//            }
//            binding.resultDraw.setImageResource(R.drawable.draw)
//            binding.result.text = getString(R.string.draw)
//        }else if ((user==2 && cpu==0) || ((user+1)==cpu)){
//            if (CountApp().getAddCount() <= CountApp().getCount()){
//                CountApp().setWinCount()
//            }
//            binding.resultDraw.setImageResource(R.drawable.win)
//            binding.result.text = getString(R.string.win)
//        }else{
//            if (CountApp().getAddCount() <= CountApp().getCount()){
//                CountApp().setLoseCount()
//            }
//            binding.resultDraw.setImageResource(R.drawable.lose)
//            binding.result.text = getString(R.string.lose)
//        }
//
//        when(cpu) {
//            0 -> binding.cpuHand.setImageResource(R.drawable.j_gu02)
//            1 -> binding.cpuHand.setImageResource(R.drawable.j_ch02)
//            else -> binding.cpuHand.setImageResource(R.drawable.j_pa02)
//        }
//
//        when(user) {
//            0 -> binding.userHand.setImageResource(R.drawable.j_gu02)
//            1 -> binding.userHand.setImageResource(R.drawable.j_ch02)
//            else -> binding.userHand.setImageResource(R.drawable.j_pa02)
//        }
//
//        binding.nextBattle.text = if (CountApp().getCount() == CountApp().getAddCount()) {
//            getString(R.string.next_result)
//        }else if (CountApp().getAddCount() == 1){
//            getString(R.string.next_battle)
//        }else{
//            getString(R.string.next_scene)
//        }
//
//        binding.nextBattle.setOnClickListener{
//            continueOrEnd()
//        }
    }

//    private fun continueOrEnd(){
//        var conOrEnd:Intent
//        val first = Intent(application,MainActivity::class.java)
//        val end = Intent(application,FinalResultActivity::class.java)
//        val con = Intent(application,HalfwayProgressActivity::class.java)
//        val game:Int = CountApp().getCount()
//        val rounds:Int = CountApp().getAddCount()
//        val win:Int = CountApp().getWinCount()
//        val lose:Int = CountApp().getLoseCount()
//
//        conOrEnd = if (rounds==1){ first }
//        else{ con }
//
//        if (CountApp().getBattleFormat() == 1){
//            if ((win-lose) > (game-rounds)){
//                conOrEnd = end
//            }else if ((lose-win) > (game-rounds)){
//                conOrEnd = end
//            }else if (CountApp().getDrawCount() > (game/2)){
//                conOrEnd = end
//            }
//        }
//
//        if (game==rounds){
//            conOrEnd = end
//        }else if (game < rounds){
//            conOrEnd = end
//        }
//
//        startActivity(conOrEnd)
//    }
}