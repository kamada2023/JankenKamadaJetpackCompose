package com.example.jankenkamadajetpackcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables", "SourceLockedOrientationActivity", "StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//
//        val count:Int = CountApp().getAddCount()
//        val guHand = 0
//        val chHand = 1
//        val paHand = 2
//
//        if (count == 0) {
//            binding.battleShout.text = getString(R.string.title)
//        }else{
//            binding.battleShout.text = getString(
//                R.string.rounds,CountApp().getAddCount()+1)
//        }
//
//        val intent = Intent(application,ResultActivity::class.java)
//        binding.Gu.setOnClickListener {
//            intent.putExtra("hand",guHand)
//            startActivity(intent)
//        }
//        binding.Ch.setOnClickListener {
//            intent.putExtra("hand",chHand)
//            startActivity(intent)
//        }
//        binding.Pa.setOnClickListener {
//            intent.putExtra("hand",paHand)
//            startActivity(intent)
//        }
    }
}