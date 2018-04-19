package com.justinpauga.cyphersystemscharactertracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.new_character.*

class NewCharacter() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_character)
    }

    fun cancelNewCharacter(view: View) {
        val cancelNewIntent = Intent()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun saveNewCharacter(view: View) {

            var character = Array(15, {""})
            character[0] = create_name.text.toString()
            character[1] = create_descriptor.text.toString()
            character[2] = create_type.text.toString()
            character[3] = create_focus.text.toString()
            character[4] = create_tier.text.toString()//.toInt()
            character[5] = create_effort.text.toString()//.toInt()
            character[6] = create_xp.text.toString()//.toInt()
            character[7] = create_might.text.toString()//.toInt()
            character[8] = create_speed.text.toString()//.toInt()
            character[9] = create_intellect.text.toString()//.toInt()
            character[10] = create_abilities.text.toString()
            character[11] = create_attacks.text.toString()
            character[12] = create_cyphers.text.toString()
            character[13] = create_equipment.text.toString()
            character[14] = create_notes.text.toString()

            sendCharacter(view, character)
    }

    fun sendCharacter(view: View, character: Array<String>) {
        var intent = Intent()
        intent.putExtra("Character", character)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}