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

            var character = Character::class.java.newInstance()
            if(create_name.text.toString() == "") {
                val toast = Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setName(create_name.text.toString())
            }
            if(create_descriptor.text.toString() == "") {
                val toast = Toast.makeText(this, "Please enter a descriptor", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setDescriptor(create_descriptor.text.toString())
            }
            if(create_type.text.toString() == "") {
                val toast = Toast.makeText(this, "Please enter a type", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setType(create_type.text.toString())
            }
            if(create_focus.text.toString() == "") {
                val toast = Toast.makeText(this, "Please enter a focus", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setFocus(create_focus.text.toString())
            }
            if(create_tier.text.toString().toInt() < 1) {
                val toast = Toast.makeText(this, "Please enter a valid tier", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setTier(create_tier.text.toString().toInt())
            }
            if(create_effort.text.toString().toInt() < 0) {
                val toast = Toast.makeText(this, "Please enter a valid effort", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setEffort(create_effort.text.toString().toInt())
            }
            if(create_xp.text.toString().toInt() < 0) {
                val toast = Toast.makeText(this, "Please enter a valid number for xp", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setXp(create_xp.text.toString().toInt())
            }
            if(create_might.text.toString().toInt() < 1) {
                val toast = Toast.makeText(this, "Please enter a valid might value", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setMight(create_might.text.toString().toInt())
            }
            if(create_speed.text.toString().toInt() < 1) {
                val toast = Toast.makeText(this, "Please enter a valid speed value", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setSpeed(create_speed.text.toString().toInt())
            }
            if(create_intellect.text.toString().toInt() < 1) {
                val toast = Toast.makeText(this, "Please enter a valid intellect value", Toast.LENGTH_LONG)
                toast.show()
                return
            }
            else {
                character.setIntelligence(create_intellect.text.toString().toInt())
            }

            character.setAbilities(create_abilities.text.toString())
            character.setAttacks(create_attacks.text.toString())
            character.setCyphers(create_cyphers.text.toString())
            character.setEquipment(create_equipment.text.toString())
            character.setNotes(create_notes.text.toString())

            sendCharacter(view, character)
    }

    fun sendCharacter(view: View, character: Character) {
        var intent = Intent()
        intent.putExtra("Character", character)
        finish()
    }
}