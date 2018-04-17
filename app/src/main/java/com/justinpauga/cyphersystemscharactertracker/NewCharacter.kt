package com.justinpauga.cyphersystemscharactertracker

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
        val cancelNewIntent = Intent(this, MainActivity::class.java)
        startActivity(cancelNewIntent)
    }

    fun saveNewCharacter(view: View) {
        if (isCharacterSaved(create_name.text.toString())) {
            val cancelNewIntent = Intent(this, MainActivity::class.java)
            startActivity(cancelNewIntent)
        }
        else {
            val toon = Character::class.java.newInstance()
            toon.setName(create_name.text.toString())
            toon.setDescriptor(create_descriptor.text.toString())
            toon.setType(create_type.text.toString())
            toon.setFocus(create_focus.text.toString())
            toon.setTier((create_tier.text.toString().toInt()))
            toon.setEffort(create_effort.text.toString().toInt())
            toon.setXp(create_xp.text.toString().toInt())
            toon.setMight(create_might.text.toString().toInt())
            toon.setSpeed(create_speed.text.toString().toInt())
            toon.setIntelligence(create_intellect.text.toString().toInt())
            toon.setAbilities(create_abilities.text.toString())
            toon.setAttacks(create_attacks.text.toString())
            toon.setCyphers(create_cyphers.text.toString())
            toon.setEquipment(create_equipment.text.toString())
            toon.setNotes(create_notes.text.toString())


        }
    }

    private fun isCharacterSaved(name: String): Boolean {
        return false
    }
}