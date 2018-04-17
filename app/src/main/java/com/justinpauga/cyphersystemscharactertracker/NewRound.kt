package com.justinpauga.cyphersystemscharactertracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class NewRound() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_round)
    }
    fun endRoundOnClick(view: View){
        val endRoundButton = Intent(this, MainActivity::class.java)
        startActivity(endRoundButton)
    }
}