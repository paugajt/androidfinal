package com.justinpauga.cyphersystemscharactertracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.character_info.*

class CharacterInfo() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_info)
    }

    fun newRoundButtonIntent (view: View){
        val newRoundButton = Intent(this, NewRound::class.java)
        startActivity(newRoundButton)
    }
    fun editCharacterInfo(view: View){
        val editButton = Intent(this, NewCharacter::class.java)
        startActivity(editButton)
    }

    fun getCharacterInfo(view: View){
        val getChar = Character::class.java.newInstance()
        getChar.getName()
    }
}